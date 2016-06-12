#version 130

uniform float blur;
uniform vec4 bounds;

out vec4 pos;

void main() {
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
    pos = gl_Vertex;

    gl_FrontColor = gl_Color;
}