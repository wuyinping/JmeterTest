/**
 *
 */


import dddd.AES;
import dddd.HttpUtil;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import java.lang.*;

/**
 * @author
 *
 */
public class Jtest extends AbstractJavaSamplerClient {

    /**
     *
     */
//    private static long start = 0;
//    private static long end = 0;

    private static String label = "Jmeter_Test ";    //定义label名称，显示在jmeter的结果窗口
    private String url;
    private String data;


    String pr;
    AES myaes;
    String param;



    /**
     * 执行runTest()方法前会调用此方法,可放一些初始化代码
     */
    public void setupTest(JavaSamplerContext arg0) {
        url = arg0.getParameter("url");
        data = arg0.getParameter("data");
        SampleResult sr;
        sr = new SampleResult();
        sr.setSampleLabel(label);




        // 开始时间
//        start = System.currentTimeMillis();
    }

    /**
     * 执行runTest()方法后会调用此方法.
     */
    public void teardownTest(JavaSamplerContext arg0) {

        // 结束时间
//        end = System.currentTimeMillis();
        // 总体耗时
//        System.err.println("cost time:" + (end - start) / 1000);
    }


    /**
     * JMeter界面中可手工输入参数,代码里面通过此方法获取
     */
    public Arguments getDefaultParameters() {

        //参数定义，显示在前台，也可以不定义
        Arguments params = new Arguments();
//        params.addArgument("url", "http://apitest.duoku.com:8082/iosyy/loginbaidu");
//        params.addArgument("data", "{\"channel\":\"700\",\"gameversion\":\"10\",\"username\":\"zhangyazhou_os@duoku.com\",\"connecttype\":\"3\",\"screenwh\":\"320.000000_568.000000\",\"imei\":\"a688e0ac08636657dc59f4668d7f4da2\",\"os\":\"iPhone OS 7.1\",\"tag\":\"102\",\"version\":\"1.0.0\",\"appid\":\"102\",\"ua\":\"iPhone Simulator\",\"udid\":\"a58e9ba678bb203a62c97ed2502bfc88\",\"appkey\":\"5t6y7u8i\",\"sessionid\": \"496B679192E3EE0267CDB8576BA72261\",\"bduserid\": \"1396745792\",\"91userid\":\"124242\",\"bduss\":\"VzR3FwdVJxSm1XY2gtVVR2TX5-NWJSdm1LLXM0dS1TdE5PZ0dlSUV5TlFFdmRUQVFBQUFBJCQAAAAAAAAAAAEAAADoU59EemhvdXRlc3Q4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFCFz1NQhc9TdT\",\"bdptoken\":\"fafafafafafaf\",\"91session\":\"fafafafafafaf\",\"bdlogintype\":\"1\"}");
        params.addArgument("data","");
        params.addArgument("url","");


        return params;

    }



    /**
     * JMeter测试用例入口
     */

    public SampleResult runTest(JavaSamplerContext arg0) {

        SampleResult sr = new SampleResult();

        try {

            // Start
            sr.sampleStart();
            myaes = new AES();
            String encryptdata = myaes.aesEncrypt(data);
            //调用被压测接口的方法
            String res = HttpUtil.http(url,encryptdata);
            System.out.println(myaes.aesDecrypt(res));
            String result = myaes.aesDecrypt(res);
           // sr.setSuccessful(false);
            // TODO
            if (result.contains("成功")){

                sr.setSuccessful(true);
                sr.setResponseCode("0000");
                sr.setResponseMessage( "接口返回成功" );
                System.out.println("成功");

            }
            else
            {
                sr.setSuccessful(false);
                sr.setResponseCode( "0001" );
                sr.setResponseMessage( "接口返回失败" );
                System.out.println("失败");
            }

            // End
            sr.sampleEnd();

        } catch (Exception e) {

            e.printStackTrace();
        }

        // End
        sr.sampleEnd();
        return sr;
    }

    /**

     * 获取线程名称，用于打印日志

     * @return

     */

    private String whoAmI() {

        StringBuilder sb = new StringBuilder();

        sb.append(Thread. currentThread ().toString());

        sb.append( "@" );

        sb.append(Integer. toHexString (hashCode()));

        return sb.toString();

    }





    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Arguments p = new Arguments();
        p.addArgument("url", "http://apitest.duoku.com:8082/iosyy/loginbaidu");
        p.addArgument("data", "{\"channel\":\"700\",\"gameversion\":\"10\",\"username\":\"zhangyazhou_os@duoku.com\",\"connecttype\":\"3\",\"screenwh\":\"320.000000_568.000000\",\"imei\":\"a688e0ac08636657dc59f4668d7f4da2\",\"os\":\"iPhone OS 7.1\",\"tag\":\"102\",\"version\":\"1.0.0\",\"appid\":\"102\",\"ua\":\"iPhone Simulator\",\"udid\":\"a58e9ba678bb203a62c97ed2502bfc88\",\"appkey\":\"5t6y7u8i\",\"sessionid\": \"496B679192E3EE0267CDB8576BA72261\",\"bduserid\": \"1396745792\",\"91userid\":\"124242\",\"bduss\":\"VzR3FwdVJxSm1XY2gtVVR2TX5-NWJSdm1LLXM0dS1TdE5PZ0dlSUV5TlFFdmRUQVFBQUFBJCQAAAAAAAAAAAEAAADoU59EemhvdXRlc3Q4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFCFz1NQhc9TdT\",\"bdptoken\":\"fafafafafafaf\",\"91session\":\"fafafafafafaf\",\"bdlogintype\":\"1\"}");
        Jtest test = new Jtest();
        JavaSamplerContext arg0 = new JavaSamplerContext(p);
        test.setupTest(arg0);
        test.runTest(arg0);
        test.teardownTest(arg0);
    }








}
