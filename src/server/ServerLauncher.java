package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
<<<<<<< HEAD
import java.rmi.registry.Registry;
=======
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de

import shared.CONSTANTS;

public class ServerLauncher {
	public static void main(String[] args) {
		System.out.println("Initializing TTTService...");
		try {
<<<<<<< HEAD
			Registry registry = LocateRegistry.createRegistry(CONSTANTS.servicePort);
			ServerService cserv = new ServiceImplementation();
			String serverObjectName = "rmi://localhost:" + CONSTANTS.servicePort + "/"
					+ CONSTANTS.ServiceName;
			registry.rebind(serverObjectName, cserv);
=======
			LocateRegistry.createRegistry(CONSTANTS.servicePort);
			ServerService cserv = new ServiceImplementation();
			String serverObjectName = "rmi://localhost:" + CONSTANTS.servicePort + "/"
					+ CONSTANTS.ServiceName;
			Naming.bind(serverObjectName, cserv);
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
			System.out.println("TTTService running.");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
