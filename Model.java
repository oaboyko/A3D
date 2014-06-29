import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;


public class Model {
	
	private String name, object_file_path;
	private static BufferedReader br;
	private Pattern v_pattern = Pattern.compile("^v\\s");
	private Pattern vt_pattern = Pattern.compile("^vt\\s");
	private Pattern vn_pattern = Pattern.compile("^vn\\s");
	private Pattern f_pattern = Pattern.compile("^f\\s");
	private int num_vertices, num_positions, num_texels, num_normals, num_faces = 0;
	float[][] positions, normals, texels;
	int[][] faces;
	
	public Model(String model_name, String model_object_file_path) throws Exception{
		name = model_name;
		object_file_path = model_object_file_path;
		getObject();
	}
	
	private void getObject() throws IOException{
		String line;
		
		br = new BufferedReader(new FileReader(object_file_path));
		
		while((line = br.readLine()) != null){
			if(v_pattern.matcher(line).find()){
				System.out.println("Vertex: " + line);
				num_positions++;
			}
			else if(vt_pattern.matcher(line).find()){
				System.out.println("Vertex Texture: " + line);
				num_texels++;
			}
			else if(f_pattern.matcher(line).find()){
				System.out.println("Face: " + line);
				num_faces++;
			}
			else if(vn_pattern.matcher(line).find()){
				System.out.println("Vertex Normal: " + line);
				num_normals++;
			}
		}
		
		br.close();
		
		num_vertices = num_faces*3;
		
		positions = new float[num_positions][3];
		normals = new float[num_normals][3];
		texels = new float[num_texels][2];
		faces = new int[num_faces][9];
	}
	
	public void printModelInfo(){
		System.out.println(name + " Model Info:");
		System.out.println("\tPositions: " + num_positions);
		System.out.println("\tTexels:    " + num_texels);
		System.out.println("\tNormals:   " + num_normals);
		System.out.println("\tFaces:     " + num_faces);
		System.out.println("\tVertices:  " + num_vertices);
	}
	
}
