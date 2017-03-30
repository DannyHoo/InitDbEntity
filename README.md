
InitDbEntity-v1.0.0

【项目说明】
本项目(v1.0.0)主要作用是把excel表格中设计好的数据库表生成对应的java类文件和对应的sql建表语句

【项目入口】
本项目的入口为com.danny.tools.initdbeneity.main.Execute

【使用方式】
1、参考com.danny.tools.initdbeneity.main.Execute.main
excelPath为excel所在目录
prefixName为javabean所在的包名(不包含javabean的父级目录)，就是上面【注意事项】-3中的"******"的实际的值。

【注意事项】
1、适用的excel模板在doc文件夹下，名为template.xlsx，需要严格按照模板中的格式
2、excel文件暂时只适用excel2007格式(后缀为.xlsx)
3、为了生存javabean后，更好的分类，模板中每个sheet的名字都是将来每个javabean的父级目录名，
比如模板中名为school的sheet中的两个表(t_student_info、t_class_info)生成javabean后，
他们的路径分别为:******.school.StudentInfo、******.school.ClassInfo
