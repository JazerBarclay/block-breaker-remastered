 

public class Debug {
	
	private static boolean isDebugMode = false;

	public static synchronized void setDebugMode(boolean state) {
		isDebugMode = state;
	}
	
	public static synchronized boolean isDebugMode() {
		return isDebugMode;
	}
	
	public static void trace(String fmt, Object... params) {
		if (isDebugMode) {
			synchronized (Debug.class) {
				System.out.printf(fmt, params);
				System.out.println();
			}
		}
	}
	
	public static synchronized void error(String fmt, Object... params) {
		System.out.printf("ERROR: " + fmt, params);
		System.out.println();
	}
	
}
