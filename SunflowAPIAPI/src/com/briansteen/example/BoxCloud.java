package com.briansteen.example;

import java.awt.Color;

import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;

import com.briansteen.SunflowAPIAPI;

public class BoxCloud {
	private SunflowAPIAPI sunflow = new SunflowAPIAPI();
	private int sceneWidth = 640;
	private int sceneHeight = 480;
	private int boxAmount = 250;
	public BoxCloud() {
		// set width and height
		sunflow.setWidth(sceneWidth);
		sunflow.setHeight(sceneHeight);
		// set background color
		sunflow.setBackground(1f, 1f, 1f);
		// set camera
		sunflow.setCameraPosition(0, 15, 5);
		sunflow.setCameraTarget(5, 5, 0);
		sunflow.setThinlensCamera("thinLensCamera", 50f, (float)sceneWidth/sceneHeight);
		// set basic light
		sunflow.setPointLight("myPointLight", new Point3(0,5,5), new Color(255,255,255));
		sunflow.setDirectionalLight("myDirectionalLight", new Point3(-2,3,0), new Vector3(0,0,0), 3, new Color(1f,1f,1f));
		sunflow.setSphereLight("mySphereLight", new Point3(0,30,-5), new Color(0,0,255), 32, 10);
		// draw a ground plane
		sunflow.drawPlane("ground", new Point3(0,0,0), new Vector3(0,1,0));
		// box start position
		float boxX = 0;
		float boxY = 0;
		float boxZ = 0;
		
		for(int i=0;i<boxAmount;i++) {
		
			// create xyz positions / size / rotation
			
			boxX += .1f + (float)Math.cos(i * .5f) * .3f;
			boxY += (float)Math.sin(boxZ)*.25f + (float)Math.cos(i + boxY)*.25f;
			boxZ += (float)Math.sin(i)*.25f + boxY*.01f;
			
			float sizeX = .35f + (float)Math.atan(boxX)*1.25f;
			float sizeY = .35f;
			float sizeZ = .35f;
			
			float rotationX = (float)Math.cos(boxX);
			float rotationY = (float)Math.sin(boxX);
			float rotationZ = (float)Math.cos(i*.5f);
			
			sunflow.setPointLight(i + "myPointLight", new Point3(boxX,boxY,boxZ+10), new Color(255,255,255));
			
			// set ambient occlusion shader
			sunflow.setAmbientOcclusionShader("myAmbientOcclusionShader", new Color(255,255,255), new Color(0,0,0), 16, 1);
			// set glass shader
			// sunflow.setGlassShader("myGlassShader", new Color(1f,1f,1f), 2.5f, 3f, new Color(1f,1f,1f));
			// set shiny-diffuse shader
			// sunflow.setShinyDiffuseShader("myShinyShader", new Color(255,255,255), 1.2f);
			// draw an object
			sunflow.drawBox(i + "box", sizeX, sizeY, sizeZ, boxX, boxY, boxZ, rotationX, rotationY, rotationZ);
		}
		sunflow.setIrradianceCacheGIEngine(32, .4f, 1f, 15f, null);
		
		// render
		sunflow.render();
	}
	public static void main(String args[]) {
		BoxCloud main = new BoxCloud();
	}
}