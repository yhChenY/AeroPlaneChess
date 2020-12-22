完整启动聊天室需先进行：
1.运行ServerRunner，服务器启动
2.三个demo(demoChen,demoVan,demoFeng)分别是三个client，运行即可连接


想要取得chatPanel（包括整个聊天室），调用ChatRoom.getGui().getChatRoomPanel()
实例
    JFrame frame = new JFrame();
    frame.add(new ChatRoom("Van").getGui().getChatRoomPanel());
    frame.setVisible(true);


传输游戏数据:
完整运行： 先运行ServerRunner, 再运行3个demo，使client连接到server

使用指南：
在需要传输数据的地方需要有Client对象（暂定由外往里传）
（测试时可以在demo处（以demoVan为例），setClient（chatRoom1.getClient()）
刚进行完自己轮的玩家调用client.transmit(String gameData, Socket socket)
gameData中各个数据用'分割
gameData = "[gameData] SelfColor String\n" (String为目标游戏数据)
socket: 刚进行完游戏的玩家对应的Client调用: client.getSocket()
接收: 其他Client 调用getNewGameData(), 即可获得String(目标游戏数据)(注意先传输完再接收)
