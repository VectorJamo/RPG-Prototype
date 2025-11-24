#version 330 core

out vec4 color;

in vec4 v_Color;
in vec2 v_textCoord;
in float v_textIndex;

in vec2 v_FragScreenPosition;

uniform sampler2D u_Textures[8];

uniform vec4 u_ColorMultiplier;

uniform bool u_IsNight;
uniform vec2 u_LightScreenPosition;

void main() 
{
	float lightIntensity = 1.0;
	if(u_IsNight)
	{
		lightIntensity = 50.0 * (1.0/distance(v_FragScreenPosition, u_LightScreenPosition));
		lightIntensity = clamp(lightIntensity, 0.0, 1);
	}
	
	
	color = lightIntensity * texture(u_Textures[int(v_textIndex)], v_textCoord) * v_Color * u_ColorMultiplier;
}
