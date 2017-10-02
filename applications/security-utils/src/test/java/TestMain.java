/**
 * 
 */

/**
 * @author EFRAIN
 * @dateCreated 1 may. 2017 21:10:34
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "com/security/template/web/xls/userReport.xls";
		
		String[] chars = {"/","\\"};
		for (String string : chars) {
			if (path.contains(string)) {
				int a = path.lastIndexOf(string);
				System.out.println(a);
				String b = path.substring(a+1,path.length());
				System.out.println(b);
			}
		}

	}

}
