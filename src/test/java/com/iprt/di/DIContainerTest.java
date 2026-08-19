package com.iprt.di;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.iprt.di.container.DIContainer;
import com.iprt.di.annotations.Injectable;
import com.iprt.di.annotations.Singleton;
import com.iprt.di.annotations.Inject;
import com.iprt.di.exception.CircularDependencyException;
import com.iprt.di.exception.BeanNotFoundException;

public class DIContainerTest {
	
   @Injectable
   static class ServiceA {
  }

   @Injectable
   static class ServiceB {
   @Inject
   ServiceA serviceA;
 }

    @Injectable
    static class SimpleService {
    }
    
    @Injectable
    static class ServiceC {
	  @Inject
	  private ServiceA serviceA;
  }
  
    @Injectable
    static class ServiceD {
		@Inject
		private ServiceA serviceA;
		@Inject 
		private ServiceC serviceC;
	}
	
	@Injectable 
	static class ServiceE {
		@Inject
		private ServiceF serviceF;
	}
	
	@Injectable 
	static class ServiceF {
		@Inject
		private ServiceE serviceE;
	}
	
	@Injectable
	@Singleton
	static class SingletonService {
	}
	
	@Injectable
	static class ServiceG {
		ServiceA serviceA;
		
		@Inject
		public ServiceG(ServiceA serviceA) {
			this.serviceA = serviceA; 
		}
	}
	
	@Test
	public void testConstructorInjectionWorks() throws Exception {
		DIContainer container = new DIContainer();
		container.register(ServiceA.class);
		container.register(ServiceG.class);
		
		ServiceG service = container.resolve(ServiceG.class);
		assertNotNull(service.serviceA);
	}
	
	@Test
	public void testFieldInjectionWorks() throws Exception {
		DIContainer container = new DIContainer();
		container.register(ServiceA.class);
		container.register(ServiceB.class);
		
		ServiceB service = container.resolve(ServiceB.class);
		assertNotNull(service.serviceA);	
	}
	
	@Test
	public void testUnregisteredTypeThrowsException() throws Exception {
		DIContainer container = new DIContainer();
		assertThrows(BeanNotFoundException.class, () -> {
			container.resolve(SimpleService.class);
		});
	}
	
	@Test
	public void testCircularDependencyThrowsException() throws Exception {
		DIContainer container = new DIContainer();
		container.register(ServiceE.class);
		container.register(ServiceF.class);
		
		assertThrows(CircularDependencyException.class, () -> {
			container.resolve(ServiceE.class);
		});
	}
	
	@Test
	public void testPrototypeReturnsDifferentInstance() throws Exception {
		DIContainer container = new DIContainer();
		container.register(SimpleService.class);
		SimpleService service1 = container.resolve(SimpleService.class);
		SimpleService service2 = container.resolve(SimpleService.class);
        assertNotSame(service1, service2);
	}
	
	@Test 
	public void testSingletonReturnsSameInstance() throws Exception {
		DIContainer container = new DIContainer();
		container.register(SingletonService.class);
		SingletonService service1 = container.resolve(SingletonService.class);
		SingletonService service2 = container.resolve(SingletonService.class);
		assertSame(service1, service2);
	}
	
	@Test
	public void testFullDependencyTree() throws Exception {
		DIContainer container = new DIContainer();
		container.register(ServiceA.class);
		container.register(ServiceC.class);
		container.register(ServiceD.class);
		ServiceD service = container.resolve(ServiceD.class);
		assertNotNull(service);
	}

    @Test
    public void testRegisterAndResolveSimpleClass() throws Exception {
        DIContainer container = new DIContainer();
        container.register(SimpleService.class);
        SimpleService service = container.resolve(SimpleService.class);
        assertNotNull(service);
    }
    
    @Test
    public void testResolveClassWithOneDependency() throws Exception {
    DIContainer container = new DIContainer();
    container.register(ServiceA.class);
    container.register(ServiceB.class);
    ServiceB service = container.resolve(ServiceB.class);
    assertNotNull(service);
}
}
