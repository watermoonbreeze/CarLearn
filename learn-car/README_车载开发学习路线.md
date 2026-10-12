# Android 车载开发学习路线（AAOS / Android Auto）

> 面向已有 Java/Android 开发经验、正在学习 Kotlin 的开发者。**建议与 `README_Kotlin_车载适配版.md` 分开维护**：Kotlin 文档管语言能力，本文件管车载平台与实战。  
> **已确认优先级（2026-10-12）**：第一阶段先学 **AAOS 车机 App 开发**，能独立完成和测试应用后，第二阶段进入 **系统应用 / Framework**，第三阶段再学习 **AOSP / VHAL 底层平台**。Android Auto 作为应用路线的对照和可选分支。  
> 学习方式：知识点 → 官方资料 → Java/Kotlin 对照 → 实验 → 测试 → Review → 单题测验。**未做的任务不标记完成。**

## 三阶段学习策略（已确定）

> **不能因为未来想学 Framework，就把当前的 M3 Lambda / M4 Flow 课程跳过去。** Kotlin 主线维持 M1～M5；本文件 C0～C10 按下面三阶段安排。每个阶段至少有一个能独立交付的项目，完成验收后再升级。

| 阶段 | 当前优先级 | 学习内容 | 对应本教程 | 阶段验收 |
|---|---|---|---|---|
| **A：车机 App** | **现在学** | AAOS 环境、UX、安全限制、生命周期、Media3、Car App Library、架构、Flow、调试和测试 | C0、C1、C2、C3、C4、C5、C6、C9（应用部分）、C10-A/B | 独立设计、运行、测试一个车机应用；处理异常、断连、状态恢复；能解释发布类别限制 |
| **B：系统应用 / Framework** | A 验收后 | Android Binder/AIDL、Car API、Car Service、系统权限、用户与电源、系统服务调试 | C7、C8（Car Service 部分）、C9（系统部分）、C10-C（上半） | 在授权测试镜像中追踪 App → Car Service 调用，能解释权限、回调与错误传播 |
| **C：底层 / OEM 平台** | B 验收后 | AOSP 构建、AIDL VHAL、HAL 生命周期、SELinux、Linux、车辆信号和 OEM 集成 | C8（VHAL / AOSP）、C9（平台部分）、C10-C（下半） | 在模拟或授权环境中追踪一个车辆属性从 HAL 到应用，提供调试记录和测试 |

**阶段切换条件**：不是按学习天数，也不是只看是否读过章节。能独立编码、运行、定位问题、写测试并说明限制，才算通过；若某项不足，保持当前阶段补课。

**分支与边界**：AAOS 车载自定义练习 App（例如模拟车辆仪表盘）可以用于学习，不代表一定符合 Google Play 车载应用发布类别；车载媒体、导航等场景必须按目标平台的类别和体验规则设计。普通应用无法仅靠在 Manifest 中声明受限权限来获得任意车身控制能力。

### 与 Kotlin 课程并行的实际顺序

1. **现在**：Kotlin **M3.1 Lambda 与高阶函数** → M3 其他核心章节；车载侧并行阅读 **C0**，准备 **C1**。
2. **接着**：Kotlin **M4 协程/Flow**、**M5 Android 工程化**；车载侧进入 **C2～C4**，把假车辆数据回调转成 `StateFlow`。
3. **App 实战**：按目标选择 **C5 媒体** 或 **C6 车载模板应用**，并贯穿 **C9 测试**；完成 **C10** 的一个 App 项目。
4. **App 阶段验收通过后**：开始 **C7、C8 系统部分**，补充 Binder/AIDL 和系统权限。
5. **系统阶段验收通过后**：再进入 **C8 平台部分**，学习 AOSP 构建、VHAL、SELinux 与 OEM 集成。

### 第一阶段建议做的两个项目

- **项目 A0：模拟车辆状态面板（纯教学项目）**。使用 `data class` / `sealed interface` / Repository / `StateFlow` / Fake 数据源实现连接、刷新、断连、重试、错误状态；测试状态一致性。它是**模拟车机 UI 实验**，不是默认可在 Google Play 发布的驾驶态仪表盘应用，也不读取真实车速。
- **项目 A1：车载媒体或受支持模板应用**。媒体优先练习 Media3、`MediaSession`、浏览和播放控制；模板应用优先练习 Car App Library 的 `CarAppService` / `Screen` / 模板及类别限制。根据官方支持类别选择，不强行把任意 Activity 视为驾驶态车载应用。

### 第二阶段前置专项

- Java/Kotlin 混合工程、Android 进程与 Service、Binder 基础、AIDL 接口和线程模型、权限模型、`dumpsys` / Logcat / bugreport。
- `Car` / `CarPropertyManager` 接口、权限与属性可用性、重连和异常状态；优先 Fake/模拟器，使用真实能力需获得开发环境和权限。
- Car Service、系统应用与普通 APK 的部署/签名差异；多用户和车载电源管理。

### 第三阶段前置专项

- Linux 进程、文件权限、构建工具、Android 构建系统与 C++ 基础；按目标 Android 分支学习 AIDL VHAL。
- VHAL 属性定义、读写/订阅、模拟 VHAL、SELinux、OEM 属性、车载信号链路；**不要在生产车辆上尝试未经授权的写操作**。

---

## 0. 先分清三条开发路线

| 路线 | 运行位置 | 核心工作 | 推荐优先级 |
|---|---|---|---|
| Android Auto | 手机应用投射到车载屏幕 | 使用受支持的车载应用框架、模板和类别 | 按岗位 |
| AAOS 普通应用 | 车机 Android 系统 | 车载 UI、媒体、导航等受支持应用 | **主线** |
| AAOS 系统/车厂开发 | 车机系统镜像和系统服务 | VHAL、Car Service、系统权限、OEM 集成 | 进阶分支 |

**边界**：Android Auto 不等于 AAOS；`CarPropertyManager` 并非普通第三方应用可任意读取车辆数据；权限、车型、API 等级及 OEM 配置决定可用能力。驾驶时分心限制、驾驶安全和真实车辆测试必须遵守平台与企业规范。

## 学习总览

| 模块 | 主题 | 前置 | 状态 |
|---|---|---|---|
| C0 | 车载生态与开发路线 | Android 基础 | [ ] |
| C1 | AAOS 开发环境和调试 | C0 | [ ] |
| C2 | 车载 UX、限制与多屏 | C1 | [ ] |
| C3 | 生命周期、任务栈与多用户 | C1、Kotlin M2 | [ ] |
| C4 | 车载应用架构与数据流 | Kotlin M3、M4 | [ ] |
| C5 | 媒体应用 | C2～C4 | [ ] |
| C6 | 导航与 Car App Library | C2～C4 | [ ] |
| C7 | Car API、属性与权限（阶段 B） | 完成阶段 A | [ ] |
| C8 | Car Service（阶段 B）、VHAL/AOSP（阶段 C） | C7、系统方向前置 | [ ] |
| C9 | 性能、稳定性、安全与测试 | C3～C8 | [ ] |
| C10 | 综合项目与验收 | 所选路线 | [ ] |

## C0 车载生态认知

**知识点**：Android Auto / AAOS / Google built-in 的区别；普通应用与系统/特权应用；Android 版本、车载 API、车厂差异；支持的应用类别和发布审核；驾驶态与停车态。

**实验**：画出“手机应用—投射系统”和“车机本地应用—AAOS—Car Service—VHAL”的架构图；为三个假想需求选技术路线。

**验证点**：能解释为什么普通 APK 不能默认获取车速、空调和车门状态。

## C1 环境搭建与调试

**知识点**：Android Studio、Kotlin/Gradle、AAOS 模拟器与系统镜像、ADB、Logcat、设备属性、模拟器限制；不同镜像服务能力不一致。

**实验**：创建最小 AAOS 示例应用，在合适的模拟器上安装启动；使用 `adb shell dumpsys activity`、`logcat` 定位启动与生命周期问题；记录 Android/API 版本。

**验收**：提交环境记录、运行截图/日志、一次故障定位说明。没有可用镜像时用受支持的模拟环境或官方示例替代，不把模拟结果视为真实车辆验证。

## C2 车载 UX、分心限制与多屏

**知识点**：Driver Distraction / UX Restrictions；车载模板与自定义 UI 的适用边界；横竖屏、旋钮/触摸/语音输入、不同屏幕密度与尺寸；驾驶态和停车态；无障碍与文本可读性。

**实验**：设计“驾驶中”和“停车时”两种交互状态；对照官方指南审查一次信息密度、点击步骤和禁用操作。

**验收**：写出限制矩阵：场景、可显示内容、允许操作、回退行为；不得用隐藏限制的方式绕过平台约束。

## C3 生命周期、多用户和任务栈

**知识点**：Activity/Service 生命周期、前后台、任务栈、多显示屏；AAOS 多用户、访客、用户切换；车机启动与休眠恢复；进程重启和状态恢复。

**实验**：实现可保存恢复的设置页；模拟进程被杀、用户切换（环境支持时）、配置变化；核对状态是否泄露到其他用户。

**验收**：解释 Activity 生命周期与车机电源状态并非一一对应；提供恢复测试表。

## C4 Kotlin 架构与异步数据流

**前置**：M3 Lambda/高阶函数、作用域函数、集合；M4 协程、Flow、取消与异常。

**知识点**：Repository、UseCase、ViewModel、StateFlow、SharedFlow、回调适配 `callbackFlow`、`awaitClose`、生命周期感知收集、线程边界、超时和重连；不可把 Binder 回调当作主线程回调。

**实验**：模拟车辆状态源（不接真实车控），把 Java 回调转成 Flow；处理连接断开、重复事件、快速更新、取消订阅；UI 展示加载/正常/错误/断连状态。

**验收**：能解释热流/冷流，取消时解除注册，避免内存泄漏与主线程阻塞；为状态流写单元测试。

## C5 媒体应用专项

**知识点**：Media3、MediaSession、媒体浏览与播放服务、音频焦点、车载媒体浏览体验、播放状态同步、通知与后台行为；具体集成能力按目标系统与支持政策核对。

**实验**：用公开音频或本地测试素材实现播放/暂停/上一首/下一首；模拟音频焦点变化、断网和进程恢复。

**验收**：提供状态图和自动化测试，保证车载 UI 与播放状态一致。

## C6 导航与 Car App Library

**知识点**：Android for Cars App Library、模板化 UI、支持类别、Session/Screen、导航任务与权限、路线状态更新；区分 Android Auto 与 AAOS 的部署和审核要求。

**实验**：从官方示例起步做一个符合允许类别的模板演示（可用模拟导航数据）；测试不同显示尺寸、返回栈和生命周期。

**验收**：能指出哪些界面不能随意用普通手机 Activity 代替车载模板，以及何时必须按类别和审核要求设计。

## C7 Car API、车身属性与权限

**知识点**：`Car` 连接、Car Service、`CarPropertyManager`、VehicleProperty、属性类型/区域/状态/变化模式、读写权限、连接断开；不同版本和 OEM 的支持差异。

**实验**：先写 `VehicleDataSource` 接口和 Fake 实现，再在授权的开发环境中尝试接入受支持的 Car API；设计“不可用/无权限/未连接/错误/有效值”五种状态。

**验收**：不假设所有车辆属性可读可写；不能用普通应用权限访问受限车辆控制能力；真实车辆操作必须由有资质的团队和安全流程授权。

## C8 VHAL、Car Service 与 AOSP（系统方向选修）

**知识点**：Android Framework/Binder/AIDL、Car Service、Vehicle HAL（VHAL）、属性映射、权限和 SELinux、系统/特权签名、OEM 集成、AOSP 构建与调试；新旧 HAL 接口和版本差异需以目标分支为准。

**实验**：阅读 AOSP 中一条模拟车辆属性从 HAL 到客户端的调用链；在合法授权的模拟环境中记录数据路径和错误处理。

**验收**：画出 App → Car API → Car Service → VHAL → 模拟硬件的链路；解释为何普通 APK 无法仅靠声明权限获得系统特权。

## C9 工程质量与车载专项测试

**知识点**：JUnit/MockK、`runTest`、Fake 车辆数据源、集成测试、ADB/dumpsys、ANR/崩溃、内存与启动性能、Binder 死亡、断网、设备休眠、用户切换；权限最小化、隐私、日志脱敏。

**实验**：为 C4 的数据流覆盖断连重试、重复事件、无权限、快速更新、取消注册；对 C5/C6 选定应用做一次异常恢复演练。

**验收**：提交测试矩阵、日志、缺陷修复说明；不把模拟器通过等同于实车通过。

## C10 综合项目（从一条路线选择）

### A. AAOS 状态仪表盘（推荐起步）
用**模拟数据**显示车速/档位/续航等状态，提供状态异常、重连、用户切换模拟；使用 Kotlin、StateFlow、Repository、测试。该项目是教学模拟器，**不宣称拥有真实车辆数据访问权限**。

### B. 车载媒体播放器
Media3 + MediaSession + 生命周期与音频焦点 + UI 状态同步 + 测试。

### C. 车厂系统集成实验（仅有授权环境）
Fake VHAL / 目标 AOSP 分支的 Car Service 调试、属性链路追踪、权限分析；不在生产车辆上执行未授权写入。

**交付**：README、架构图、模块图、Kotlin 源码、单元测试、异常场景测试、兼容性说明、可演示视频/截图。

## Kotlin 学习路线的衔接

| Kotlin 章节 | 车载实际用途 |
|---|---|
| M1 空安全、类型、集合 | 处理缺失车辆数据、枚举/状态 |
| M2 sealed/data class、接口、委托 | 状态模型、数据源抽象、包装与适配 |
| M3 Lambda、高阶函数、扩展、Sequence | 回调、事件转换、数据管道 |
| M4 协程/Flow | 车辆状态异步订阅、取消、重连 |
| M5 互操作、Android 工程化 | Java SDK、Binder 接口封装、Gradle、测试 |
| M5.6 车载桥接 | 从语言练习过渡到 AAOS 模拟项目 |

**已确定顺序**：先完成 M3.1 Lambda → M3 其他核心内容 → M4 协程/Flow；C0、C1 可并行，C4 以后与 Kotlin 进度同步。通过车机 App 项目验收后才推进系统应用 / Framework，最后进入底层平台。

## 每节教学与考核模板

1. 先明确目标、所需 Kotlin 前置知识和 Android 平台限制。
2. 讲概念、架构、Java/Kotlin 对照、典型故障及安全边界。
3. 给可运行的模拟数据示例，不提前给练习完整答案。
4. 学习者独立实现一个小功能，进行代码 Review。
5. 每次一道题，错误先提示，再允许第二次尝试。
6. 记录“能解释/能写/能测试/能定位故障”四级达标情况。

## 官方资料入口（版本、适用类别和权限以目标平台为准）

- [Android for Cars 总览](https://developer.android.com/training/cars)
- [Android Automotive OS](https://developer.android.com/training/cars/platforms/automotive-os)
- [Android for Cars App Library](https://developer.android.com/training/cars/apps/library)
- [Android for Cars 支持的应用类别](https://developer.android.com/training/cars)
- [车载应用发布与质量要求](https://developer.android.com/training/cars/distribute)
- [Android 媒体开发](https://developer.android.com/media)
- [Android Lifecycle 协程](https://developer.android.com/topic/libraries/architecture/coroutines)
- [AOSP Automotive](https://source.android.com/docs/automotive)
- [AOSP Vehicle HAL](https://source.android.com/docs/automotive/vhal)

> 维护约定：每个模块完成后更新日期、运行环境、代码链接、失败案例与复盘。实际设备与 OEM 能力以项目文档和授权测试环境为准。
