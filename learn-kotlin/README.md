[toc]

# LearnKotlin — Kotlin 学习知识点

> Java Android 多年 → Kotlin。学习方式：**知识点梳理 → 逐步细化 → 自己写代码验证 → 教学模式**。
> 与根目录 `README.md` 的「## Kotlin」对比表和 `大纲.md` 的 Kotlin 侧计划（阶段0~3）对应。

## 学习约定

- 每个知识点按「看概念 → 自己写代码 → 对照「验证点」自查 → 不会的标记待教学」推进
- 代码写在 `learn-kotlin/app/src/main/java/com/sxd/learnkotlin/` 下，按模块建包（`basic/`、`oop/`、`functional/`、`coroutine/`、`interop/`）
- 学完一个模块，在「模块总览」勾选 `[x]` 并记日期
- 每一条「验证点」是你自己动手的代码题，写完后我帮你 review 是否符合惯用法

## 模块总览

| 模块 | 主题 | 对应大纲 | 状态 |
|------|------|---------|------|
| M1 | 基础语法 | 阶段0 | [ ] |
| M2 | 面向对象 | 阶段1 | [ ] |
| M3 | 函数式与高级特性 | 阶段1~2 | [ ] |
| M4 | 协程 | 阶段1~2 | [ ] |
| M5 | Java 互操作与工程化 | 阶段2~3 | [ ] |

---

## M1 基础语法

### 1.1 变量与类型
- 知识点：`val`/`var`、类型推断、基础类型（Int/Long/Double/Boolean/Char）、可空类型 `?`、类型转换（`as`/`toInt()`/智能转换）、`Any`/`Unit`/`Nothing`
- 验证点：声明只读/可变变量；可空与非空类型互相赋值；数值与字符串互转

### 1.2 函数
- 知识点：`fun`、参数、默认参数、具名参数、单表达式函数、可变参数 `vararg`、局部函数
- 验证点：写带默认参数的函数；单表达式函数；`vararg` 求和函数

### 1.3 控制流
- 知识点：`if`/`when` 作为表达式、`for`/`while`/`do-while`、区间（`..`/`downTo`/`until`/`step`）、`break`/`continue`、`label` 标签、`when` 的三种分支（值/类型/条件）
- 验证点：用 `when` 替代 Java 的 `switch`；用 `when` 做类型判断；区间循环

### 1.4 空安全
- 知识点：`?.`、`?:`、`!!`、`?.let{}`、`lateinit`、`by lazy`、`as?` 安全转换、平台类型
- 验证点：链式空安全调用；Elvis 给默认值；区分 `lateinit` 与 `lazy` 的使用场景

### 1.5 集合
- 知识点：`List`/`Set`/`Map`、可变/不可变（`listOf` vs `mutableListOf`）、创建与遍历、集合操作符（`map`/`filter`/`groupBy`/`fold`/`reduce`/`flatMap`/`any`/`all`）
- 验证点：用操作符链处理一个列表，对比 Java 的 for 循环写法

### 1.6 字符串
- 知识点：字符串模板 `$`、三引号 `"""`、`trimIndent`、字符串操作（`split`/`join`/`substring`）
- 验证点：拼接多行字符串；模板里嵌表达式

---

## M2 面向对象

### 2.1 类与对象
- 知识点：类声明、主/次构造函数、`init` 块、属性与 getter/setter、幕后字段 `field`、可见性（`public`/`internal`/`protected`/`private`）、嵌套类 / `inner` 内部类
- 验证点：写带主构造函数和 `init` 的类；自定义 getter/setter

### 2.2 继承与接口
- 知识点：`open`、`abstract`、`interface`、`override`、`super`、接口默认方法、接口多继承、属性覆盖
- 验证点：定义接口 + 抽象类 + 实现类，覆盖属性

### 2.3 特殊类
- 知识点：`data class`（自动 equals/hashCode/toString/copy/componentN）、`sealed class`/`sealed interface`、`enum class`、`object` 单例、`companion object`
- 验证点：用 `data class` 替代 Java POJO；用 `sealed class` 表达一个状态

### 2.4 泛型
- 知识点：泛型类/函数、声明处型变 `out`/`in`、类型约束、星投影 `*`、`where` 多约束、`reified`
- 验证点：写泛型函数；说清 `out`/`in` 与 Java `? extends`/`? super` 的对应

### 2.5 委托
- 知识点：类委托 `by`、属性委托 `by lazy`/`observable`/`vetoable`、`Delegates`、自定义委托（getValue/setValue）
- 验证点：用 `by lazy` 延迟初始化；用 `observable` 监听属性变化

---

## M3 函数式与高级特性

### 3.1 lambda 与高阶函数
- 知识点：函数类型 `(Int) -> String`、lambda、`it`、尾随 lambda、函数引用 `::`、高阶函数、内联 `inline`/`noinline`/`crossinline`、`reified`
- 验证点：写高阶函数；把 Java 匿名内部类改成 lambda

### 3.2 作用域函数
- 知识点：`let`/`run`/`with`/`apply`/`also` 的区别与选型（返回值 / 接收者 this vs it）
- 验证点：五个作用域函数各写一个例子，能说清区别

### 3.3 扩展函数 / 扩展属性
- 知识点：扩展函数、扩展属性、成员扩展、可空接收者、扩展是静态分派
- 验证点：给 `String`/`List` 写扩展函数

### 3.4 其他语法糖
- 知识点：解构声明 componentN、运算符重载（`plus`/`get`）、中缀函数 `infix`、类型别名 `typealias`、`@JvmName`
- 验证点：给 `data class` 解构；重载 `+` 运算符

---

## M4 协程

### 4.1 协程基础
- 知识点：`suspend`、`CoroutineScope`、`launch`/`async`、`Job`/`Deferred`、`runBlocking`、结构化并发（父子关系/取消传播）
- 验证点：用 `launch` 起协程；`async` 返回值；写一个 `suspend` 函数

### 4.2 调度器
- 知识点：`Dispatchers.Main`/`IO`/`Default`/`Unconfined`、`withContext` 切换、线程池
- 验证点：在主线程/IO 线程间切换，避免主线程阻塞

### 4.3 异常与取消
- 知识点：`try-catch`、`CoroutineExceptionHandler`、`supervisorScope`/`SupervisorJob`、`cancel`、`ensureActive`、`withTimeout`
- 验证点：处理协程异常；取消一个长时间任务

### 4.4 Flow
- 知识点：`Flow`/`flow{}`、中间/末端操作符、冷流、`StateFlow`/`SharedFlow`、热流、`collect`、背压
- 验证点：用 `flow` 发射序列；用 `StateFlow` 做状态管理

---

## M5 Java 互操作与工程化

### 5.1 Java 互操作
- 知识点：平台类型、`@Nullable`/`@NonNull`、`@JvmStatic`/`@JvmField`/`@JvmOverloads`/`@JvmName`、SAM 转换、Kotlin 调 Java / Java 调 Kotlin、Gson 序列化 `data class`
- 验证点：Kotlin 调一个 Java 类；Java 调 Kotlin 的默认参数函数

### 5.2 Android 落地
- 知识点：`viewModelScope`/`lifecycleScope`、`ViewModel`、`StateFlow` 替代 LiveData、Compose/XML 中的 Kotlin 惯用法
- 验证点：写一个 `ViewModel` + `StateFlow` 的简单页面

### 5.3 编码规范与最佳实践
- 知识点：命名规范、惯用写法（idiomatic Kotlin）、避免 `!!`、`when` 优先、不可变优先、作用域函数选型
- 验证点：把一段 Java 代码转成 idiomatic Kotlin 并 review

### 5.4 单元测试
- 知识点：JUnit4、MockK、协程测试（`runTest`/`StandardTestDispatcher`/`UnconfinedTestDispatcher`）
- 验证点：给一个工具函数 / 协程函数写单测

---

## 下一步

- 从 **M1 基础语法** 开始，每个小节「验证点」你动手写，我 review
- 每个知识点按需展开细化（可在本文件追加子小节，或拆到独立 `.md`）
- 卡住时切换教学模式：讲解 / 追问 / 复盘
