import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

public class MybatisPlusGenerator {

    /**
     * 数据库配置
     */
    private static final String DATA_BASE_URL = "jdbc:mysql://140.143.218.245:10081/solar_blog?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&&useSSL=false";
    private static final String DATA_BASE_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DATA_BASE_USERNAME = "root";
    private static final String DATA_BASE_PASSWORD = "cxl241010";

    /**
     * 创建人
     */
    private static final String AUTHOR = "cxl";
    /**
     * 包配置
     */
    private static final String PARENT_PACKAGE = "com.cxlsky";
    private static final String ENTITY_PACKAGE = "pojo.entity";
    private static final String XML_PACKAGE = "mapper";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DATA_BASE_URL);
        dsc.setDriverName(DATA_BASE_DRIVER_NAME);
        dsc.setUsername(DATA_BASE_USERNAME);
        dsc.setPassword(DATA_BASE_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT_PACKAGE);
        pc.setEntity(ENTITY_PACKAGE);
        pc.setXml(XML_PACKAGE);
        mpg.setPackageInfo(pc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
