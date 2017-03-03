package roman.easythrift.client;

import org.apache.thrift.TServiceClient;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.MethodInvoker;

import java.lang.reflect.Method;

/**
 * Created by roman.luo on 2016/5/16.
 */
@Aspect
public class ThriftClientAspect {

    @Pointcut("this(roman.easythrift.client.ThriftClient)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        ThriftClient thriftService = (ThriftClient)pjp.getTarget();
        Object result = invok(getMethod(pjp) ,pjp.getArgs(), thriftService);
        return result;
    }

    private Object invok(Method method, Object[] args, ThriftClient thriftService) throws Throwable {
        Object result;
        TServiceClient serviceClient = thriftService.buildServiceClient();
        MethodInvoker methodInvoker = new MethodInvoker();
        methodInvoker.setTargetObject(serviceClient);
        methodInvoker.setTargetMethod(method.getName());
        methodInvoker.setArguments(args);
        methodInvoker.prepare();
        result = methodInvoker.invoke();
        return result;
    }

    private Method getMethod(JoinPoint jp) {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

}
