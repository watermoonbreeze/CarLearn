# 项目公共规则（Claude / Codex 共享）

## 项目定位

- LearnCar = 个人学习仓库：`learn-car`（车载 Android 应用层开发 Demo）+ `learn-kotlin`（Kotlin 学习）。
- 求职转型：Java Android 多年 → 车载 Android 应用层（杭州富阳/滨江）。
- 学习路线见根 `大纲.md`。

## 学习边界（硬红线）

- ✅ 学：AAOS 应用、Car App Library、车载 UI/权限/状态、应用层性能优化、Kotlin、Jetpack。
- ❌ 不学：AOSP 编译、Framework 修改、内核、驱动、HAL、底层 BSP。讲解遇到底层内容直接跳过。

## 目录结构

- `learn-car/`、`learn-kotlin/`：两个独立 Gradle 工程。
- `.ai-context/`：共享资料唯一写入位置（规则/角色/Skill/工作流/模板/文档）。
- `.claude/`、`.codex/`：仅工具专属适配，不放共享源。
- 根 `大纲.md`：学习大纲（用户维护，不并入 `.ai-context`）。

## 构建 / 测试

见 `.ai-context/PROJECT.md`。

## Git 纪律

- 只在用户要求时 commit/push；提交前查 `git status`/`diff`；不用 `git add .` 混入无关文件。
- 不 amend、不 `--no-verify`、不 `reset --hard` / `push --force` / `checkout --` 覆盖用户改动。

## 发布协议

`.ai-context/` 共享资产实质变更时，同一改动更新 `ASSET_REGISTRY.md` + `VERSION.md` + `CHANGELOG.md`，详见 `SYNC_PROTOCOL.md`。
