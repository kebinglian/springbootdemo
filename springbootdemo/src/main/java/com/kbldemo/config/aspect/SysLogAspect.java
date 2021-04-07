package com.kbldemo.config.aspect;
import com.alibaba.fastjson.JSON;
import com.kbldemo.entity.Log;
import com.kbldemo.service.LogService;
import com.kbldemo.system.CusAccessObjectUtil;
import com.kbldemo.system.ReflectUtil;
import com.kbldemo.system.SystemCommUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * <p>
 *  AOP切面注解（核心）
 * </p>
 *
 * @author kbl
 * @since 2021-03-11
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private LogService sysLogService;
  /*  @Autowired
    private CustomUserDetailsService customUserDetailsService;*/

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.kbldemo.config.aspect.SysAopLog)")
    public void logPoinCut() {
    }

    //切面配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        SysAopLog myLog = method.getAnnotation(SysAopLog.class);
        String myLogValue = "";
        if (myLog != null) {
            String value = myLog.value();
            myLogValue = value;
        }
        if(myLogValue.indexOf("查询") >= 0) {

        }else{
            //保存日志
            Log sysLog = new Log();
            sysLog.setId(SystemCommUtil.randomUUID());
            sysLog.setOperationValue(myLogValue);
          /*  CustomUserDetail customUserDetail = customUserDetailsService.getCurrentCustomUser();*/

            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            sysLog.setFlag(className.substring(className.lastIndexOf(".")+1).replace("Controller",""));
            //获取请求的方法名
            String methodName = method.getName();
            sysLog.setOperationMethod(methodName);
            //请求的参数
            Object[] args = joinPoint.getArgs();
            //去除附件和在线编辑内容,
            if(args.length>0 && args[0]!=null){
                ReflectUtil.setFieldValue(args[0],"uploadFilesSystem",null);
                ReflectUtil.setFieldValue(args[0],"content",null);
                //将参数所在的数组转换成json
                String params = JSON.toJSONString(args[0]);
                if(params.length() > 2500){
                    params = params.substring(0,2500);
                }
                sysLog.setOperation(params);
            }
            sysLog.setOperationTime(new Date());
            //获取用户名

           /* User user = customUserDetail.getSysUser();
            sysLog.setUserId(user.getId());
            sysLog.setUserName(user.getFullName());*/
            //获取用户ip地址
            ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request =attr.getRequest();
            String ip = CusAccessObjectUtil.getIpAddress(request);
            if(ip.indexOf(",") >= 0) {
                ip = ip.substring(0,ip.indexOf(","));
            }
            sysLog.setClientIp(ip);

            sysLogService.save(sysLog);
        }


    }
}
