package algGeom;


public class Face {
	
	int v1, v2, v3;
	Vect3d n;
	
	public Face (int vv1, int vv2, int vv3, Vect3d normal){
		v1=vv1; v2=vv2; v3=vv3;
		n = normal;
	}
	
	public Face (int [] vertices){
		v1 = vertices[0];
		v2 = vertices[1];
		v3 = vertices[2];
		n = null;
	}
	
	public void setNormal (Vect3d normal){
		n = normal;
	}
        
        public Vect3d getNormal(){
		return n;
	}
}


