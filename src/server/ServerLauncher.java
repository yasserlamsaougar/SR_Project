package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import shared.CONSTANTS;

public class ServerLauncher {
	public static void main(String[] args) {
		System.out.println("Initializing TTTService...");
		try {
			Registry registry = LocateRegistry.createRegistry(CONSTANTS.servicePort);
			ServerService cserv = new ServiceImplementation();
			String serverObjectName = "rmi://localhost:" + CONSTANTS.servicePort + "/"
					+ CONSTANTS.ServiceName;
			registry.rebind(serverObjectName, cserv);

			System.out.println("TTTService running.");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
