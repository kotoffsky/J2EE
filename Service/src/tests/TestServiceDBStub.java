package tests;

public class TestServiceDBStub {
	 public static void main (String [] args) {
	        System.out.print("Testing class ServiceDBStub... ");
	        try {
	            service.TestServiceDB.test(new service.ServiceDBStub());
	        } catch (Throwable e) {
	            System.out.println("\nTests failed: "+e);
	            e.printStackTrace();
	            System.exit(1);
	        }
	        System.out.print("OK");
	    }
}
