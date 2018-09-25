package com.yd.generator;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class App {

	public static void main(String[] args) {
		//1. 全局配置
				GlobalConfig config = new GlobalConfig();
				config.setActiveRecord(false) // 是否支持AR模式
					  .setAuthor("yd") // 作者
					  .setOutputDir("D:\\generatorCodeMP\\src\\main\\java") // 生成路径
					  .setFileOverride(true)  // 文件覆盖
					  .setEnableCache(false)//是否开启二级缓存
					  .setIdType(IdType.AUTO) // 主键策略
					  .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
					  .setServiceImplName("%sServiceImpl")
					  .setOpen(false)//生成后是否自动打开资源文件
		 			  .setBaseResultMap(true)//xml是否生成BaseResultMap
		 			  .setBaseColumnList(true);
				
				//2. 数据源配置
				DataSourceConfig  dsConfig  = new DataSourceConfig();
				dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
						.setDriverName("com.mysql.jdbc.Driver")
						.setUrl("jdbc:mysql://192.168.0.104:3306/pub_jywydb")
						.setUsername("root")
						.setPassword("root");
				 
				//3. 策略配置
				StrategyConfig stConfig = new StrategyConfig();																									
				stConfig.setCapitalMode(true) //全局大写命名
						.setSuperEntityClass("com.yuanxin.pojo.dbo.Base")// 自定义实体父类
						/**
						 * 实体类是否使用以下这三个注解
						 * 	@Data
						 * 	@EqualsAndHashCode(callSuper = false)
						 *		1. 此注解会生成equals(Object other) 和 hashCode()方法。
						 *		2. 它默认使用非静态，非瞬态的属性
						 *		3. 可通过参数exclude排除一些属性
						 *		4. 可通过参数of指定仅使用哪些属性
						 *		5. 它默认仅使用该类中定义的属性且不调用父类的方法
						 *		6. callSuper=true。让其生成的方法中调用父类的方法。
						 * @Accessors(chain = true)：chain 一个布尔值。如果为真，产生的setter返回的是this而不是void。默认是假。但是生成会设置为真，不知道咋设置为假
						 */
						.setEntityLombokModel(true)
		                .setEntityBooleanColumnRemoveIsPrefix(true)//Boolean类型字段是否移除is前缀处理，测试2.X无效果3.X有效果，但是2.x数据库tinyint(1)不会映射成boolen,3.x会映射成boolean
						.setDbColumnUnderline(true)  // 指定表名 字段名是否使用下划线
						.setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
//						.setTablePrefix("m_")//表前缀
						.setInclude("customer_inf");  // 生成的表
				
				//4. 包名策略配置 
				PackageConfig pkConfig = new PackageConfig();
				pkConfig.setParent("com.yuanxin")
						.setMapper("mapper")
						.setXml("mapper.impl")
//						.setService("service")    //不设置，会生成默认的
//						.setServiceImpl("service.impl")
//						.setController("controller")
						.setEntity("pojo.dbo");
				
				//5. 整合配置
				AutoGenerator  ag = new AutoGenerator();
				
				ag.setGlobalConfig(config)
				  .setDataSource(dsConfig)
				  .setStrategy(stConfig)
				  .setPackageInfo(pkConfig);
				
				//6. 执行
				ag.execute();
	}
	
}
