# CarLearn

kotlin+车载 学习

> 个人求职转型学习仓库：Java Android 多年 → 车载 Android 应用层（杭州富阳/滨江）。Kotlin 与车载 Android 对半学时（50:50），总周期 5~6 个月。

## Kotlin

### 基础知识
1. 基础类型
2. 集合
3. Map
4. get set 用法
等

### Kotlin与Java之间对比

也就是kotlin中的每一类 都要找java中的对应的部分

| Kotlin | Java | 说明 |
|--------|------|------|
| `val` / `var` | `final` / 普通变量 | 只读 / 可变 |
| `fun` | 方法 | 函数 |
| `data class` | POJO + 手写 equals/hashCode/toString/copy | 自动生成 |
| `object` | 单例类 | 饿汉单例 |
| `companion object` | `static` 成员 | 伴生对象 |
| `sealed class` | 抽象类 + 枚举 | 受限继承，配合 `when` 全覆盖 |
| `when` | `switch` | 更强：任意类型、可作表达式 |
| `?.` / `?:` | `if (x != null)` | 空安全 |
| 只读/可变集合 `List`/`MutableList` | `List`（可变） | 只读可变分离 |
| 扩展函数 | 工具类静态方法 | Kotlin 特有 |
| 协程 / `suspend` | 线程 / Executor / RxJava / 回调 | 同步写法写异步 |

### 迁移到Kotlin后Java中不用的或迁移不出的属于Java的特性

1. **受检异常（checked exception）** — Kotlin 没有，全为运行时异常
2. **原始类型 vs 装箱类型**（`int`/`Integer`）— Kotlin 统一为 `Int` 等
3. **通配符泛型 `? extends` / `? super`** — 改为声明处变型 `out` / `in`
4. **匿名内部类** — 改为 lambda 或 `object` 表达式
5. **static 方法 / 字段** — 改为顶层函数 / `companion object`
6. **`new` 关键字** — Kotlin 直接 `ClassName()`
7. **显式 getter/setter 样板** — 改为属性自动生成
8. **三元运算符 `? :`** — 改为 `if-else` 表达式（`?:` 是空安全操作符，含义不同）
9. **`for(;;)` 三段式循环** — 改为 `for (i in 0 until n)`

### Kotlin中新增的特性

1. **空安全**：`?`、`?.`、`?:`、`!!`、`lateinit`、`by lazy`
2. **数据类 `data class`**：自动 equals/hashCode/toString/copy/componentN
3. **密封类 `sealed class` / `sealed interface`**：状态机、受限继承
4. **扩展函数 / 扩展属性**：不改源码给已有类加能力
5. **属性委托**：`lazy`、`observable`、自定义 `by`
6. **协程**：`suspend`、`launch`/`async`、`Flow`/`StateFlow`/`SharedFlow`、`Channel`
7. **作用域函数**：`let` / `run` / `with` / `apply` / `also`
8. **解构声明**：`val (x, y) = pair`
9. **内联函数 `inline` + `reified`**：类型擦除时拿到真实类型
10. **默认参数 / 具名参数**：减少重载

### kotlin中的语法糖

1. 字符串模板 `"hello $name"`、三引号 `"""`
2. `if` / `when` / `try-catch` 作为表达式
3. 区间与循环 `for (i in 1..10 step 2)`、`downTo`、`until`
4. 智能类型转换（smart cast）
5. 单表达式函数 `fun foo() = bar()`
6. `with` / `apply` 链式初始化
7. 集合操作符链 `map` / `filter` / `flatMap` / `groupBy` / `reduce`
8. 中缀函数 `1 to "a"`、`until`
9. 操作符重载（`plus`、`get` 等）
10. `?.let {}`、`takeIf` / `takeUnless`

### Java → Kotlin 需要注意和学习的点（迁移 + 互操作）

**空安全（最大的思维转变，也是最大的坑）**
1. Java 方法返回值默认是「平台类型」，可能为 null 但编译器不报错 → 迁移后要主动标注可空性
2. 少用 `!!`，多用 `?.` + `?:`，否则 NPE 又回到 Java 时代
3. `lateinit` 用于 Android 注入（View / DI / 依赖），`by lazy` 用于延迟计算
4. `@Nullable` / `@NonNull` 注解会被 Kotlin 识别成空安全类型

**序列化 / 框架互操作**
5. `data class` 接 Gson/Moshi：注意无参构造、`@Keep`（防混淆）、字段命名
6. `@JvmStatic` / `@JvmField`：让 `companion object` 成员变成真正 static
7. `@JvmOverloads`：默认参数对 Java 生成重载
8. `@JvmName`：解决 JVM 签名冲突

**迁移技巧**
9. AS 自带「Convert Java File to Kotlin」可自动转，但转完必须人工 review（重点：空安全、平台类型）
10. 迁移顺序建议：先工具类/POJO → 再 Activity/Fragment/Adapter → 最后引入协程
11. Java 静态工具类 → 顶层函数 + 扩展函数

**Android 落地**
12. 用 `viewModelScope` / `lifecycleScope` 替代 AsyncTask / Handler / RxJava
13. 用 `sealed class` + `when` 做 UI 状态管理，替代一堆 if-else 状态判断
14. `Flow` 替代冷流、`StateFlow` 替代热流（对应 LiveData 场景）


## 车载

### AAOS 基础认知
1. AAOS vs 手机 Android 差异（无 Google Play、车载形态、系统版本策略）
2. 车载应用形态：Car App Library 模板应用 / 系统预装 / 第三方应用
3. 开发环境：Android Studio + AAOS 模拟器（系统镜像，如 Polestar）

### Car App Library 核心
1. CarAppService / Session / Screen 三层架构
2. 模板 UI：Pane、Grid、Message、List、Map、Navigation 等标准模板
3. projected（手机投影）vs automotive（车机原生）两种模式
4. HostValidator、模板限制：车机不支持手机自定义复杂 View，必须用模板

### 车载四大组件与生命周期差异
1. Activity 车机生命周期、多窗口
2. 车辆休眠 / 唤醒对应用生命周期的影响
3. 车机上电 / 下电（ignition on/off）

### 车载权限体系
1. 车载特有权限（CAR_SPEED、CAR_ENERGY、CAR_POWERTRAIN 等）
2. 静态 / 动态 / 系统签名权限
3. 与手机 Android 权限的区别

### 车辆数据读取（Car API）
1. CarManager / CarPropertyManager
2. 读取车辆状态：车速、档位、车灯、电池、里程、燃料
3. CarProperty 订阅 / 回调 / 权限校验

### 车机状态与安全
1. 休眠、唤醒、上电、下电、应用保活 / 销毁策略
2. 驾驶态（Driving State）安全限制：驾驶中限制输入、视频播放（安全红线）

### 车载业务与适配
1. 车载媒体应用、车载通知、车载设置应用
2. 多屏幕、车机 DPI 适配

### 车载性能优化（重点，贯穿全程）
1. 内存：车机内存比手机更严格；常驻应用内存监控；休眠唤醒场景下的内存泄漏复现与修复
2. 启动优化：冷启动 / 热启动；启动任务拆分、异步加载
3. 卡顿优化：Systrace / Perfetto / Choreographer
4. ANR：休眠唤醒、车辆事件回调阻塞主线程
5. 功耗优化：后台任务、协程管理、避免不必要传感器监听

### 车载 IPC / 应用间通信
1. ContentProvider / AIDL（只应用层调用，不写底层服务）
2. 车载跨应用数据交互

### 面试高频
1. AAOS vs 手机 Android 最大差异
2. Car App Library 模板限制
3. 车载权限、驾驶态安全限制
4. 车机上电 / 下电、休眠唤醒对应用影响

---

## 项目结构

本仓库包含两个**独立 Gradle 工程**：

| 目录 | 工程名 | 定位 |
|------|--------|------|
| `learn-car/` | LearnCar | 车载应用开发 Demo（Car App Library） |
| `learn-kotlin/` | LearnKotlin | Kotlin 语法/特性快速演练 |

> 关系说明：`learn-kotlin` 用于快速过 Kotlin 语法与特性，`learn-car` 是正式车载 Demo；两个工程长期独立并存。

### learn-car（车载应用）

| 模块 | 类型 | 说明 |
|------|------|------|
| `:shared` | library | Car App Library 核心（CarAppService / Session / Screen） |
| `:mobile` | application | 手机端投影宿主（Compose 入口，app-projected） |
| `:automotive` | application | AAOS 车机端（CarAppActivity，app-automotive） |

### learn-kotlin（Kotlin 学习）

| 模块 | 类型 | 说明 |
|------|------|------|
| `:app` | application | Kotlin 学习用应用（主源码待填充） |

---

## 基础配置

| 项 | learn-car | learn-kotlin |
|----|-----------|--------------|
| Gradle | 9.6.0 | 9.6.0 |
| AGP | 9.4.1 | 9.4.1 |
| Kotlin | 2.2.10 | AGP 内置 |
| compileSdk / targetSdk | 37 / 37 | 37 / 37 |
| minSdk | 29 | 24 |
| Java 兼容 | 11 | 11 |
| UI | Compose + Car App Library | XML（Material / AppCompat） |

---

## 常用命令

```bash
# learn-car
cd learn-car && ./gradlew :automotive:assembleDebug   # 车机端 debug 包
cd learn-car && ./gradlew :mobile:assembleDebug       # 手机端 debug 包
cd learn-car && ./gradlew test                        # 单元测试

# learn-kotlin
cd learn-kotlin && ./gradlew :app:assembleDebug
cd learn-kotlin && ./gradlew test
```

> Windows 用 `gradlew.bat`。两个工程各自独立 wrapper，分别 `cd` 进入执行。

---

## 学习路线

完整学习大纲见 **[大纲.md](./大纲.md)**（Kotlin : 车载 = 50 : 50，分 4 阶段，含教学启动 Prompt）。

学习边界：**只做应用层**，不碰 AOSP 编译 / Framework / 内核 / HAL / 驱动。

---

## AI 协作配置

本项目已初始化 Claude Code + Codex 双模型工作区：

- 项目概览 / 规则：`.ai-context/`（`PROJECT.md`、`public-agent-rules.md` 等）
- Claude 入口：`CLAUDE.md`；Codex 入口：`AGENTS.md`

---

## 说明

本项目为个人学习仓库，**不对外发布**，仅用于学习与求职 Demo 展示。
