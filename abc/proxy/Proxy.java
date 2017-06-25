package proxy;

/**
 * 
 * 代理作用 隔离client对service的访问，此处隔离可以认为是难度上的隔离，例如我们找代理律师是因为我们对法律了解程度不够。
 * 
 *      <p>隔离也可理解为代码访问隔离，使得client无法直接访问service，
 *      代理模式提供了与service完全一样的接口，而对象适配器模式提供的两种不同接口直接的调用
 *      <p>代理模式和装饰模式虽然都实现了相同接口，但是代理模式 重点在于隔离，所以构造代理实例时并不需要传入Service，装饰模式则需要传入被装饰的对象
 *      <p>在Android中的跨进程通讯采用了代理机制
 *      
 *      
 * @author wangliuyang
 *
 */
public class Proxy implements FunctionInterface{
	private FunctionInterface fuc;
	public Proxy() {
		fuc=new Service();
	}
	@Override
	public void methodA() {
		//转发之前，如果有参数可以进行参数准备，
		fuc.methodA();//将客户端的请求转发给service
		//调用返回之后同样也可以做其他处理
		
	}

	@Override
	public void methodB() {
		fuc.methodB();
		
	}

}
