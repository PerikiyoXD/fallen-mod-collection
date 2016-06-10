#version 330 core

uniform float blur;

in vec3 posIn;

out vec3 posOut;


void main() {
    posOut = posIn;
}