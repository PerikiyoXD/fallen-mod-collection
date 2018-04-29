package net.thefallenmoon.fmc.core.utils.modeldump;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.registries.GameData;
import net.thefallenmoon.fmc.core.FallenCoreMod;

public class ModelDumper {
    private ICommandSender sender;
    private final Path outPath;
    private final BlockRendererDispatcher blockRendererDispatcher;
    private final BlockModelShapes blockModelShapes;
    private final Gson gson = new Gson();
    private Path modelPath;
    private Path texturePath;

    public ModelDumper(ICommandSender sender, String outPath) {
        this.sender = sender;
        this.outPath = Paths.get(outPath);
        this.modelPath = this.outPath.resolve("models");
        this.texturePath = this.outPath.resolve("textures");

        Minecraft minecraft = Minecraft.getMinecraft();
        blockRendererDispatcher = minecraft.getBlockRendererDispatcher();
        blockModelShapes = blockRendererDispatcher.getBlockModelShapes();
    }

    public void dumpModels() {
        try {
            Files.createDirectories(this.outPath);
            Files.createDirectories(this.modelPath);
            Files.createDirectories(this.texturePath);
        } catch (IOException e) {
            e.printStackTrace();
            sender.sendMessage(new TextComponentString("Failed to create output directory"));
            return;
        }

        ObjectIntIdentityMap<IBlockState> blockStateIDMap = GameData.getBlockStateIDMap();

        long count = 0;
        long total = StreamSupport.stream(blockStateIDMap.spliterator(), false)
                .filter(v -> v != null)
                .count();

        sender.sendMessage(new TextComponentString("Discovered " + total + " block states."));
        for (Object o : blockStateIDMap) {
            IBlockState state = (IBlockState) o; //I have no idea why this broke...
            try {
                dumpState(state);
            } catch (Throwable e) {
                FallenCoreMod.logger.warn("Error processing state: " + state.toString(), e);
            }
            count++;
            if (count % 500 == 0) {
                FallenCoreMod.logger.info("Dumping: " + count + "/" + total);
                sender.sendMessage(new TextComponentString("Dumping: " + count + "/" + total));
            }
        }

        sender.sendMessage(new TextComponentString("Done."));
    }

    private void dumpState(IBlockState state) throws IOException {
        BlockModel result = new BlockModel();

        result.name = state.getBlock().getRegistryName().toString();
        result.stateName = state.toString();
        result.opaque = state.isFullCube();
        result.meta = state.getBlock().getMetaFromState(state);

        IBakedModel model = blockModelShapes.getModelForState(state);

        boolean foundQuads = false;
        for (QuadFacing facing : QuadFacing.values()) {
            List<BakedQuad> quads = model.getQuads(state, facing.dir, 0);
            List<DumpedQuad> dumpedQuads = new ArrayList<>(quads.size());

            if (quads.size() > 0) {
                foundQuads = true;
            }

            for (BakedQuad quad : quads) {
                dumpTextureIfNeeded(quad.getSprite());

                QuadConverter converter = new QuadConverter();
                converter.setVertexFormat(DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
                quad.pipe(converter);
                converter.dumpedQuad.texture = getTextureID(quad.getSprite());
                dumpedQuads.add(converter.dumpedQuad);
            }

            result.quads.put(facing, dumpedQuads);
        }

        if (!foundQuads) {
            FallenCoreMod.logger.warn("Found no quads for " + result.stateName);
        }

        String idStr = getBlockPath(state);
        Path statePath = modelPath.resolve(idStr + ".json");
        Files.createDirectories(statePath.getParent());
        Files.write(statePath, gson.toJson(result).getBytes(StandardCharsets.UTF_8));
    }

    private String getBlockPath(IBlockState state) {
        Block block = state.getBlock();
        return block.getRegistryName().toString().replace(":", "/") + "/" + block.getMetaFromState(state);
    }

    private void dumpTextureIfNeeded(TextureAtlasSprite sprite) throws IOException {
        BufferedImage texImg = getBufferedImage(sprite);
        String idStr = getTextureID(sprite);
        Path texPath = outPath.resolve("textures").resolve(idStr + ".png");
        if (!Files.exists(texPath)) {
            Files.createDirectories(texPath.getParent());
            ByteArrayOutputStream texOutBytes = new ByteArrayOutputStream();
            ImageIO.write(texImg, "png", texOutBytes);
            Files.write(texPath, texOutBytes.toByteArray());
        }
    }

    private String getTextureID(TextureAtlasSprite sprite) {
        return sprite.getIconName().replace(":", "-").replace('.', '_');
    }

    private static BufferedImage getBufferedImage(TextureAtlasSprite textureAtlasSprite) {
        int iconWidth = textureAtlasSprite.getIconWidth();
        int iconHeight = textureAtlasSprite.getIconHeight();
        int frameCount = textureAtlasSprite.getFrameCount();

        BufferedImage bufferedImage = new BufferedImage(iconWidth, iconHeight * frameCount, BufferedImage.TYPE_4BYTE_ABGR);
        for (int i = 0; i < frameCount; i++) {
            int[][] frameTextureData = textureAtlasSprite.getFrameTextureData(i);
            int[] largestMipMapTextureData = frameTextureData[0];
            bufferedImage.setRGB(0, i * iconHeight, iconWidth, iconHeight, largestMipMapTextureData, 0, iconWidth);
        }

        return bufferedImage;
    }
}
