package test;

import java.util.regex.Pattern;

import test.packagea.FactoryImpl2;
import test.packagea.Type;


public class Test {
	

	public static void main(String[] args) {
//		Factory t=new FactoryImpl();
//		t.produce();
//		Factory ta=new FactoryImpl2();
//		ta.produce();
//		Singleton<Test> tSingleton=new Singleton<Test>() {
//			
//			@Override
//			protected Test create() {
//				
//				return new Test();
//			}
//		};
//			
//		for (int i = 0; i < 10; i++) {
//			System.out.println(tSingleton.getInstance());
//		}
//		
//		Scanner scanner=new Scanner(System.in);
//		while (true) {
//			String line=scanner.nextLine();
//			String level=getLevel(line);
//			System.out.println(level);
//			String pattern="^\\d+(\\.\\d{2,3}5$)?";//匹配一个整形或者有小数部分的，有小数必须以五结尾
//			System.out.println(Pattern.matches(pattern, line));
//			String s="^\\d+(\\.\\d+5$)?";//匹配以多为数字开头的整数或者有小数的，如果有小数部分必须以5结尾
//			System.out.println(Pattern.matches(s, line));
//			
//			String p="(\\d{1,3}\\.){3}\\d{1,3}";//IP简易规则
//			//+ * ? $ ^ \d \w \D \W [a-z] {n} {n,} {n,m} \s \S  [^abc]  x|y
//		}
//		
//		String ssd=Comparetor.max("abc","abb");
//		System.out.print(ssd);
//		
//		int min=Comparetor.min(5, 4);
//		System.out.print(""+min);
//		
//		Testa tsTesta=Publes.getTesta();
//		tsTesta.test();
//		
//		try {
//			ZipUtil.zip("/Users/wangliuyang/wdc/standard","/Users/wangliuyang/wdc/shell","acbad.zip");
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		try {
//			ZipUtil.unZip("/Users/wangliuyang/wdc/shell/acbad.zip", "/Users/wangliuyang/wdc/shell/acbad");
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
		
//		try {
//			Class a=Class.forName("");
//			Connection c=DriverManager.getConnection("");
//			java.sql.Statement stmt=c.createStatement();
//			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		byte a=charToByte('A');
		
		System.out.println(Character.valueOf((char)a));
	}
	
	
	  /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
	
	private static void test() {
		Type t=Type.AS;
		System.out.println(Type.values()[0]);
		System.out.println(Type.valueOf(Type.class, "AS"));
		System.out.println(Type.valueOf("BO"));
		System.out.println(Type.AS.name());
		System.out.println(""+Type.AS.getType());
		System.out.println(Type.AS);
		System.out.println(Type.AS.toString());
		
		System.out.println(Type.AS.ordinal());
		
		System.out.println(Type.AS.getDeclaringClass().getSimpleName());
		
	}

	static String weakPattern="(\\d|[a-z]|[A-Z]){4,6}";
	static String midPattern="[0-9a-z]+|[0-9A-Z]+|[a-zA-Z]+";
	private static String getLevel(String line) {
		if (Pattern.matches(weakPattern, line)) {
			return "weak";
		}
		if (Pattern.matches(midPattern, line)) {
			return "middle";
		}
		return "strong";
	}
	
}
