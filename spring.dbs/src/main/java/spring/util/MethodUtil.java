package spring.util;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.Modifier;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by frinder6 on 2016/12/23.
 */
public class MethodUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodUtil.class);

    /**
     * 定义 class pool
     */
    private static final ClassPool pool = ClassPool.getDefault();

    /**
     * 获取方法参数列表
     *
     * @param className
     * @param methodName
     * @return
     * @throws NotFoundException
     */
    public static Set<String> getMethodArgName(String className, String methodName) throws NotFoundException {
        LOGGER.info("class name is : " + className + ", method name is : " + methodName);
        // 返回参数列表
        Set<String> result = new HashSet<>();
        // 根据类名获取操作类
        CtClass ctClass = pool.get(className);
        // 根据方法名获取目标方法
        CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
        // 获取方法参数个数
        int length = ctMethod.getParameterTypes().length;
        LOGGER.info("arg count is : " + length);
        // 无参数时
        if (length > 0) {
            // 获取方法信息
            MethodInfo methodInfo = ctMethod.getMethodInfo();
            // 获取方法属性
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attribute = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
            for (int i = 0; i < length; i++) {
                result.add(attribute.variableName(i + pos));
            }
        }
        LOGGER.info("the method : " + methodName + ", args are : " + result.toString());
        return result;
    }


}
