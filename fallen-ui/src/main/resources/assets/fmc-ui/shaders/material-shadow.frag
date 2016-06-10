#version 330 core

in vec3 pos;

out vec4 color;

void main() {
    gl_FragDepth = 5;
    color = vec4(1, 1, 1, 0);
}