package zychaowill.discover.smallskill.communication.pc.backup;

public class Run {
	public static void main(String[] args) {
		DBTools dbTools = new DBTools();
		for (int i = 0; i < 20; i++) {
			BackupB output = new BackupB(dbTools);
			output.start();
			BackupA input = new BackupA(dbTools);
			input.start();
		}
	}
}
