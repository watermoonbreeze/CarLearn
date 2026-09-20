# 项目级资产注册表

> 规范源：`.ai-context/`。新增/修改角色、工作流、Skill、Prompt、Hook 必须同步更新本表 + `CHANGELOG.md`。

## 继承关系

本项目**无项目级专属** Agent / Skill / 工作流 / Hook，全部继承用户级 `~/.ai-context/`（24 通用角色 + 16 通用 Skill + 双模型编排 + Hook）。下表登记项目级现状。

| 资产类别 | 规范源 | Claude 入口 | Codex 入口 | 状态 |
|---|---|---|---|---|
| 项目规则 | `public-agent-rules.md` | 根 `CLAUDE.md` | 根 `AGENTS.md` | 已建立 |
| 项目概览 | `PROJECT.md` | 根 `CLAUDE.md` 链接 | 根 `AGENTS.md` 链接 | 已建立 |
| 发布协议 | `SYNC_PROTOCOL.md` | 直读 | 直读 | 已建立 |
| 学习大纲 | 根 `大纲.md` | 直读 | 直读 | 用户维护 |
| 项目级 Agent | 无 | — | — | 未建立（继承用户级） |
| 项目级 Skill | 无 | — | — | 未建立（继承用户级） |
| 项目级 Hook | 无 | `.claude/settings.json` | `.codex/config.toml` | 未建立 |

## 命令映射

| Claude 命令 | 共享 Skill | Codex | 状态 |
|---|---|---|---|
| /myinit、/zongjie、/quickref-scan、/autotest 等 | 继承用户级 | 继承用户级 | 已同步（用户级） |
