# LearnCar 构建与兼容说明

本项目包含手机端 `mobile`、车载端 `automotive` 和共享车载业务库 `shared`。
构建配置采用 Android Studio Hedgehog 兼容基线，同时可以由较新的 Android Studio 打开和构建。

## 推荐开发环境

| 项目 | 版本 |
| --- | --- |
| Android Studio | Hedgehog 2023.1.1（推荐） |
| Android Gradle Plugin | 8.2.2 |
| Gradle Wrapper | 8.2 |
| Gradle JDK | JDK 17 |
| Kotlin | 1.9.22 |
| Java/Kotlin 字节码目标 | 11 |
| Android SDK Compile SDK | 34 |
| Android SDK Target SDK | 34 |
| 最低 Android SDK | 29 |

Android Studio Iguana 2023.2.1 及更高版本通常也可以直接打开本项目；如果 IDE 提示升级 AGP，保持当前 AGP 8.2.2 即可，避免重新生成高版本 Gradle 配置。

## SDK 与新特性兼容说明

这次调整主要是构建工具链兼容：将 AGP 9/Gradle 9/Kotlin 2 的配置降到 AGP 8.2.2、Gradle 8.2、Kotlin 1.9.22，并把 AGP 9 专属 DSL 改为 AGP 8.2 支持的写法。它不会把运行设备降级到 Android 9；AndroidX 和车载库的 API 依赖也没有因为构建工具降级而替换。

`compileSdk` 和 `targetSdk` 统一为 34，表示本项目按 Android 14 SDK 编译和声明目标版本；`minSdk = 29` 仍表示最低支持 Android 10。这样会影响“能否直接编译 Android 15/16 新 API”，也会让系统针对更高 target SDK 的部分新行为暂不启用，但不会影响 Android 10～14 的既有代码运行。若以后要直接使用 Android 15/16 的新 API，需要把 compile SDK 升级，并同时评估 AGP、Gradle、Kotlin 与 Android Studio 的版本组合；新 API 的运行时仍需按版本做 API 检查或兼容调用。

Compose 使用 Kotlin 1.9.22 对应的 Compose Compiler 1.5.8 和 Compose BOM 2024.02.02。现有 Compose、AndroidX、Car App 代码保持不变；仅不能直接使用依赖 Kotlin 2.x 或更新 Compose Compiler 才提供的新语言/编译器能力，除非后续整体升级工具链。

## 构建命令

```powershell
./gradlew.bat :mobile:assembleDebug
./gradlew.bat :automotive:assembleDebug
```

首次同步或构建前，请在 Android Studio 的 **Settings > Build, Execution, Deployment > Build Tools > Gradle** 中选择 JDK 17，并安装 Android SDK Platform 34。

