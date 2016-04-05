# 代码生成器
## 1、运行
* Windows环境运行 run.bar
* Linux环境运行run.sh （需要桌面环境支持）
* 其他环境运行命令：
``` shell
    $ java -cp generator-swing.jar org.hsweb.generator.swing.SwingGeneratorApplication
```

* 快捷键，变量设置和模板设置中的表结构表格支持CTRL+V快捷键。可直接从剪切板中粘贴（如果无法粘贴，请先创建一行并选中，粘贴后删掉即可）
## 2、配置变量
* 变量可自由设置，在模板中可直接引用

## 3、模板配置
* 表结构定义
* * 表格1 为结构定义表，表头可通过修改config/header.cfg.json进行自定义。
* * 表格2为模板定义表，可自定义要进行生成的模板。模板语言使用freemarker
输出路径也可以写表达式，如${target}/po/${module}/${beanName}.java ，表 	达式中的变量为变量设置中定义的变量。
## 4、代码生成
* 点击生成代码面板中的开始进行生成代码，日志会在控制台中打印
## 4、模板说明
###### 模板引擎使用freemarker。内置对象有：
1. 所有变量设置中的变量。
2. 其他内置对象
 
|               变量                 |            说明                        | 
| -----------------------------------|:--------------------------------------:| 
| tableMeta                          | 结构定义                                     | 
| tableMeta.name                     | 表名，通过变量 table.name进行设置|
| tableMeta.comment                  | 表备注，通过变量table.comment进行设置 |
| tableMeta.fields                   | 配置的字段信息，类型为 List<org.webbuilder.sql.FieldMetaData>  [查看类型详情](https://github.com/hs-web/webbuilder/blob/master/wb-sql-util/src/main/java/org/webbuilder/sql/FieldMetaData.java)                  |
| tableMeta.attr(String name)        |获取自定义的属性(在结构表头定义配置文件(config/header.cfg.json)中，没有与默认属性关联上的属性，将通过此方法获取)               |