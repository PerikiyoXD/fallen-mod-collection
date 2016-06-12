#version 130

uniform float blur;
uniform vec4 bounds;

in vec4 pos;

#define OFFSET_BLUR_MULT (3)
#define LEFT_OFFSET (normalizedBlur * OFFSET_BLUR_MULT)
#define RIGHT_OFFSET (normalizedBlur * OFFSET_BLUR_MULT)
#define TOP_OFFSET (normalizedBlur * OFFSET_BLUR_MULT)
#define BOTTOM_OFFSET (-normalizedBlur * OFFSET_BLUR_MULT)

void main() {
    float normalizedBlur = (blur / 32);

    float leftDist = bounds[0] - pos.x + LEFT_OFFSET;
    float rightDist = pos.x - bounds[1] + RIGHT_OFFSET;
    float topDist = bounds[2] - pos.y + TOP_OFFSET;
    float bottomDist = pos.y - bounds[3] + BOTTOM_OFFSET;

    bool left = leftDist > 0;
    bool right = rightDist > 0;
    bool top = topDist > 0;
    bool bottom = bottomDist > 0;

    float dist = 0;
    if (top && left) {
        float ydist = topDist;
        float xdist = leftDist;
        dist = sqrt(xdist * xdist + ydist * ydist);

    } else if (top && right) {
        float ydist = topDist;
        float xdist = rightDist;
        dist = sqrt(xdist * xdist + ydist * ydist);

    } else if (bottom && left) {
        float ydist = bottomDist;
        float xdist = leftDist;
        dist = sqrt(xdist * xdist + ydist * ydist);

    } else if (bottom && right) {
        float ydist = bottomDist;
        float xdist = rightDist;
        dist = sqrt(xdist * xdist + ydist * ydist);

    } else if (left) {
        dist = leftDist;

    } else if (right) {
        dist = rightDist;

    } else if (bottom) {
        dist = bottomDist;

    } else if (top) {
        dist = topDist;
    }

    float normalizedDist = (max(dist - 0.2, 0) / 32) / normalizedBlur;
    float baseAlpha = 0.10;
//    if (normalizedDist <= 0) {
//    baseAlpha = 0.5;
//    }
    float alpha = baseAlpha - (pow(normalizedDist, 0.8) * .3);
    gl_FragColor = vec4(0,0,0,alpha);
}