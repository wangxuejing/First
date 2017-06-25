package facade;
/**
 * 外观模式 computer作为一个外观 提供给用户，用户不需要知道其内部的组织关系，降低了系统的复杂度；
 * <p>再比如，一个开关面板上有多个开关，控制整个大楼的照明系统和电梯系统,对于用户来讲开关面板降低了整个系统的复杂度
 * <p><strong>外观模式优点:</strong><p>客户端只需要与外观类交互，使得整个系统更加容易使用，降低了客户端与子系统的耦合；只提供了子系统的唯一访问入口，并不影响用户对子系统的使用；
 * 降低了大型软件系统中的编译依赖，简化了在不同系统之间的移植；
 * 
 * <p><strong>外观模式缺点：</strong><p>
 * 
 * @author wangliuyang
 *
 */
public class Computer {
	private Memory memory;
	private CPU cpu;
	private Disk d;
	public Computer() {
		memory=new Memory();
		cpu=new CPU();
		d=new Disk();
	}
	
	public void computerStrartUp(){
		System.out.println("start computer up");
		cpu.startUp();
		memory.startUp();
		d.startUp();
	}
	
	public void computerShuntDown() {
		System.out.println("shut computer down ");
		cpu.shutDown();
		memory.shutDown();
		d.shutDown();
	}
	
}
