import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		String img = "", cmd = "";
		ImageDecoder imgd = new ImageDecoder();
		if (args.length > 0) {
			cmd = args[0];
			if (cmd.equals("dec"))
				imgd.decodeRGB(args[1], System.currentTimeMillis() + "");
			else
				imgd.encodeRGB(args[1], System.currentTimeMillis() + "");
			System.exit(0);
		}

		System.out.println("RGBEncoder\nCommands:\ndec <filepath.png> | Decode png stored bytes to file\nenc <filepath> | Encode bytes from file to png\nexit");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print(">> ");
			img = sc.nextLine();
			cmd = img.substring(0, 3).trim();
			img = img.substring(4).trim();
			if (cmd.equals("dec")) {
				imgd.decodeRGB(img, System.currentTimeMillis() + "");
				System.out.println("Decoded successfully!");
			} else if (cmd.equals("enc")) {
				imgd.encodeRGB(img, System.currentTimeMillis() + "");
			} else if (cmd.equals("exi"))
				System.exit(0);
		}
	}
}