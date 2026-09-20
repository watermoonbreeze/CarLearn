# LearnCar 项目概览

> 个人学习仓库：Kotlin + 车载 Android 应用层开发。双模型共享，唯一写入位置 `.ai-context/`。

## 定位

- 求职转型：38 岁、Java Android 多年 → 车载 Android 应用层（杭州富阳/滨江，优先富阳 E 类人才补贴）。
- 学习边界硬红线：只做应用层，不碰 AOSP 编译 / Framework 修改 / 内核 / HAL / 驱动 / BSP。
- 学习路线：根 `大纲.md`（Kotlin : 车载 = 50 : 50，总周期 5~6 个月）。

## 子工程（两个独立 Gradle 工程）

| 目录 | rootProject | 用途 | 状态 |
|------|-------------|------|------|
| `learn-car/` | LearnCar | 车载应用开发 Demo（Car App Library） | 脚手架已可运行 |
| `learn-kotlin/` | LearnKotlin | Kotlin 语法/特性学习演练 | 仅脚手架（无主 Activity） |

> 关系：learn-kotlin 纯语法速练，learn-car 正式车载 Demo，两工程**长期并存**。

## 技术栈

| 项 | learn-car | learn-kotlin |
|----|-----------|--------------|
| Gradle | 9.6.0（wrapper） | 9.6.0（wrapper） |
| AGP | 9.4.1 | 9.4.1 |
| Kotlin | 2.2.10（显式 + compose 插件） | AGP 内置（未显式声明版本） |
| compileSdk / target | 37 / 37 | 37 / 37 |
| minSdk | 29 | 24 |
| Java 兼容 | 11 | 11 |
| UI | Compose（mobile）+ Car App Library | XML（Material / AppCompat） |
| 车载 | androidx.car.app（app / app-projected / app-automotive） | — |
| 依赖仓库 | 阿里云镜像 + google + mavenCentral + jitpack | 同左 |

## learn-car 模块

| 模块 | 类型 | namespace | 说明 |
|------|------|-----------|------|
| `:shared` | library | com.sxd.car.shared | Car App Library 核心：CarAppService / Session / Screen（MessageTemplate） |
| `:mobile` | application | com.sxd.car | 手机端投影宿主（Compose 入口 MainActivity，app-projected） |
| `:automotive` | application | com.sxd.car | AAOS 车机端（app-automotive，CarAppActivity + templates_host） |

## learn-kotlin 模块

| 模块 | 类型 | namespace | 说明 |
|------|------|-----------|------|
| `:app` | application | com.sxd.learnkotlin | Kotlin 学习用 Android 应用（主源码待填充） |

## 构建 / 测试命令

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

## 模块路径索引（初扫目录树，未臆测源码职责）

- `learn-car/shared/src/main/java/com/sxd/car/shared/` — MyCarAppService / MyCarAppSession / MyCarAppScreen
- `learn-car/shared/src/main/res/xml/automotive_app_desc.xml` — 车载应用描述（template）
- `learn-car/mobile/src/main/java/com/sxd/car/` — MainActivity（Compose）+ ui/theme/
- `learn-car/automotive/src/main/` — AndroidManifest（CarAppActivity 入口）
- `learn-kotlin/app/src/main/` — Manifest + res（主源码待建）
