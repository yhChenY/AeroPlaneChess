import Accounts.*;
import GAMING.*;
import gui.MainMenu;

public class test {
  public static void main(String[] args) {
    Main.mainMenu = new MainMenu();
    System.out.println(MapSystem.getNthBlock(51).getNextId());
//    Block[] blocks = new Block[80];
//    blocks[0] = new Block(Color.RED, Block.Type.COMMON, 534, 94, 0, 1, 51);
//    blocks[1] = new Block(Color.YELLOW, Block.Type.COMMON, 534, 138, 1, 2, 0);
//    blocks[2] = new Block(Color.BLUE, Block.Type.COMMON, 515, 186, 2, 3, 1);
//    blocks[3] = new Block(Color.GREEN, Block.Type.FLY, 552, 223, 3, 4, 2);
//
//    blocks[4] = new Block(Color.RED, Block.Type.COMMON, 605, 209, 4, 5, 3);
//    blocks[5] = new Block(Color.YELLOW, Block.Type.COMMON, 647, 209, 5, 6, 4);
//    blocks[6] = new Block(Color.BLUE, Block.Type.COMMON, 698, 224, 6, 7, 5);
//    blocks[7] = new Block(Color.GREEN, Block.Type.COMMON, 715, 272, 7, 8, 6);
//
//    blocks[8] = new Block(Color.RED, Block.Type.COMMON, 715, 315, 8, 9, 7);
//    blocks[9] = new Block(Color.YELLOW, Block.Type.CORNER, 715, 359, 9, 10, 8); //
//    blocks[10] = new Block(Color.BLUE, Block.Type.COMMON, 715, 404, 10, 11, 9);
//    blocks[11] = new Block(Color.GREEN, Block.Type.COMMON, 715, 447, 11, 12, 10);
//
//    blocks[12] = new Block(Color.RED, Block.Type.COMMON, 695, 495, 12, 13, 11);
//    blocks[13] = new Block(Color.YELLOW, Block.Type.COMMON, 647, 514, 13, 14, 12);
//    blocks[14] = new Block(Color.BLUE, Block.Type.COMMON, 604, 514, 14, 15, 13);
//    blocks[15] = new Block(Color.GREEN, Block.Type.COMMON, 553, 495, 15, 16, 14);
//
//    blocks[16] = new Block(Color.RED, Block.Type.FLY, 516, 530, 16, 17, 15);
//    blocks[17] = new Block(Color.YELLOW, Block.Type.COMMON, 533, 578, 17, 18, 16);
//    blocks[18] = new Block(Color.BLUE, Block.Type.COMMON, 533, 623, 18, 19, 17);
//    blocks[19] = new Block(Color.GREEN, Block.Type.COMMON, 513, 671, 19, 20, 18);
//
//    blocks[20] = new Block(Color.RED, Block.Type.COMMON, 465, 687, 20, 21, 19);
//    blocks[21] = new Block(Color.YELLOW, Block.Type.COMMON, 419, 687, 21, 22, 20);
//    blocks[22] = new Block(Color.BLUE, Block.Type.CORNER, 375, 687, 22, 23, 21); //
//    blocks[23] = new Block(Color.GREEN, Block.Type.COMMON, 329, 687, 23, 24, 22);
//
//    blocks[24] = new Block(Color.RED, Block.Type.COMMON, 282, 687, 24, 25, 23);
//    blocks[25] = new Block(Color.YELLOW, Block.Type.COMMON, 232, 669, 25, 26, 24);
//    blocks[26] = new Block(Color.BLUE, Block.Type.COMMON, 214, 623, 26, 27, 25);
//    blocks[27] = new Block(Color.GREEN, Block.Type.COMMON, 214, 579, 27, 28, 26);
//
//    blocks[28] = new Block(Color.RED, Block.Type.COMMON, 233, 530, 28, 29, 27);
//    blocks[29] = new Block(Color.YELLOW, Block.Type.FLY, 195, 496, 29, 30, 28);
//    blocks[30] = new Block(Color.BLUE, Block.Type.COMMON, 146, 510, 30, 31, 29);
//    blocks[31] = new Block(Color.GREEN, Block.Type.COMMON, 101, 510, 31, 32, 30);
//
//    blocks[32] = new Block(Color.RED, Block.Type.COMMON, 51, 492, 32, 33, 31);
//    blocks[33] = new Block(Color.YELLOW, Block.Type.COMMON, 30, 446, 33, 34, 32);
//    blocks[34] = new Block(Color.BLUE, Block.Type.COMMON, 30, 403, 34, 35, 33);
//    blocks[35] = new Block(Color.GREEN, Block.Type.CORNER, 30, 360, 35, 36, 34); //
//
//    blocks[36] = new Block(Color.RED, Block.Type.COMMON, 30, 315, 36, 37, 35);
//    blocks[37] = new Block(Color.YELLOW, Block.Type.COMMON, 30, 270, 37, 38, 36);
//    blocks[38] = new Block(Color.BLUE, Block.Type.COMMON, 351, 211, 38, 39, 37);
//    blocks[39] = new Block(Color.GREEN, Block.Type.COMMON, 101, 205, 39, 40, 38);
//
//    blocks[40] = new Block(Color.RED, Block.Type.COMMON, 146, 205, 40, 41, 39);
//    blocks[41] = new Block(Color.YELLOW, Block.Type.COMMON, 195, 218, 41, 42, 40);
//    blocks[42] = new Block(Color.BLUE, Block.Type.COMMON, 231, 186, 42, 43, 41);
//    blocks[43] = new Block(Color.GREEN, Block.Type.COMMON, 215, 140, 43, 44, 42);
//
//    blocks[44] = new Block(Color.RED, Block.Type.COMMON, 215, 95, 44, 45, 43);
//    blocks[45] = new Block(Color.YELLOW, Block.Type.COMMON, 232, 45, 45, 46, 44);
//    blocks[46] = new Block(Color.BLUE, Block.Type.COMMON, 285, 30, 46, 47, 45);
//    blocks[47] = new Block(Color.GREEN, Block.Type.COMMON, 330, 30, 47, 48, 46);
//
//    blocks[48] = new Block(Color.RED, Block.Type.CORNER, 375, 30, 48, 49, 47);
//    blocks[49] = new Block(Color.YELLOW, Block.Type.COMMON, 420, 30, 49, 50, 48);
//    blocks[50] = new Block(Color.BLUE, Block.Type.COMMON, 465, 30, 50, 51, 49);
//    blocks[51] = new Block(Color.GREEN, Block.Type.COMMON, 515, 45, 51, 52, 50);
//
//    blocks[52] = new Block(Color.RED, Block.Type.SPRINT, 375, 85, 52, 53, 48);
//    blocks[53] = new Block(Color.RED, Block.Type.SPRINT, 375, 135, 53, 54, 52);
//    blocks[54] = new Block(Color.RED, Block.Type.SPRINT, 375, 180, 54, 55, 53);
//    blocks[55] = new Block(Color.RED, Block.Type.SPRINT, 375, 270, 55, 56, 55);
//    blocks[56] = new Block(Color.RED, Block.Type.SPRINT, 375, 270, 56, 57, 55);
//    blocks[57] = new Block(Color.RED, Block.Type.FINAL, 365, 305, 57, 0, 56);
//
//    blocks[58] = new Block(Color.YELLOW, Block.Type.SPRINT, 650, 360, 58, 59, 9);
//    blocks[59] = new Block(Color.YELLOW, Block.Type.SPRINT, 605, 360, 59, 60, 58);
//    blocks[60] = new Block(Color.YELLOW, Block.Type.SPRINT, 560, 360, 60, 61, 59);
//    blocks[61] = new Block(Color.YELLOW, Block.Type.SPRINT, 515, 360, 61, 62, 60);
//    blocks[62] = new Block(Color.YELLOW, Block.Type.SPRINT, 470, 360, 62, 63, 61);
//    blocks[63] = new Block(Color.YELLOW, Block.Type.FINAL, 415, 350, 63, 0, 62);
//
//    blocks[64] = new Block(Color.BLUE, Block.Type.SPRINT, 375, 625, 64, 65, 22);
//    blocks[65] = new Block(Color.BLUE, Block.Type.SPRINT, 375, 580, 65, 66, 64);
//    blocks[66] = new Block(Color.BLUE, Block.Type.SPRINT, 375, 535, 66, 67, 65);
//    blocks[67] = new Block(Color.BLUE, Block.Type.SPRINT, 375, 490, 67, 68, 66);
//    blocks[68] = new Block(Color.BLUE, Block.Type.SPRINT, 375, 445, 68, 69, 67);
//    blocks[69] = new Block(Color.BLUE, Block.Type.FINAL, 365, 390, 69, 0, 68);
//
//    blocks[70] = new Block(Color.GREEN, Block.Type.SPRINT, 95, 360, 70, 71, 35);
//    blocks[71] = new Block(Color.GREEN, Block.Type.SPRINT, 140, 360, 71, 72, 70);
//    blocks[72] = new Block(Color.GREEN, Block.Type.SPRINT, 185, 360, 72, 73, 71);
//    blocks[73] = new Block(Color.GREEN, Block.Type.SPRINT, 225, 360, 73, 74, 72);
//    blocks[74] = new Block(Color.GREEN, Block.Type.SPRINT, 270, 360, 74, 75, 73);
//    blocks[75] = new Block(Color.GREEN, Block.Type.FINAL, 315, 350, 75, 0, 74);
//
//    blocks[76] = new Block(Color.RED, Block.Type.WAIT, 545, 3, 76, 0, 0);
//    blocks[77] = new Block(Color.YELLOW, Block.Type.WAIT, 725, 525, 77, 13, 13);
//    blocks[78] = new Block(Color.BLUE, Block.Type.WAIT, 185, 700, 78, 26, 26);
//    blocks[79] = new Block(Color.GREEN, Block.Type.WAIT, 3, 175, 79, 39, 39);
//
//    for(int i=0;i<80;i++){
//      MapSystem.getBlocks().add(blocks[i]);
//      System.out.println(MapSystem.getBlocks().size());
//    }
//    MapSystem.saveBlocks();
//    System.out.println("DONE");
  
  }
}
