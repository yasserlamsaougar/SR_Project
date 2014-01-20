package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import shared.CONSTANTS;

public class ServerLauncher {
	public static void main(String[] args) {
		System.out.println("Initializing TTTService...");
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER 0 FOR MAIN SERVER AND 1 FOR BACKUP");
		int type = sc.nextInt();
		try {
			if (type == 0) {
				Registry registry = LocateRegistry
						.createRegistry(CONSTANTS.servicePort);
				ServiceImplementation cserv = new ServiceImplementation();
				cserv.setUpServer(2);
				String serverObjectName = "rmi://localhost:"
						+ CONSTANTS.servicePort + "/" + CONSTANTS.ServiceName;
				registry.rebind(serverObjectName, cserv);
			} else if (type == 1) {
				Registry registry = LocateRegistry
						.createRegistry(CONSTANTS.b_servicePort);
				ServiceImplementation cserv = new ServiceImplementation();
				cserv.setUpServer(2);
				String serverObjectName = "rmi://localhost:"
						+ CONSTANTS.b_servicePort + "/" + CONSTANTS.b_ServiceName;
				registry.rebind(serverObjectName, cserv);
			}
			System.out.println("TTTService running.");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
