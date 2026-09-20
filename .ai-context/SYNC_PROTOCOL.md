# 项目发布协议（双模型）

## 规范源

- 项目共享资产唯一写入位置：`.ai-context/`（规则/角色/Skill/工作流/Prompt/Hook/共享文档）。
- Claude 适配：`.claude/`；Codex 适配：`.codex/`。不得把共享源复制进两个专属目录。
- 项目通用规则：`public-agent-rules.md`；入口 `CLAUDE.md`、`AGENTS.md` 均链接它。

## 发布规则

1. `.ai-context/` 共享资产实质变更时，同一改动必须更新 `ASSET_REGISTRY.md`、`VERSION.md`、`CHANGELOG.md`。
2. `CHANGELOG.md` 当前版本条目必须列变更资产相对路径、Claude/Codex 适配状态、必要迁移动作。
3. 用户说“模型切换”或明确增量同步口令时，当前模型读自己的同步游标应用未处理版本；不把普通项目任务当全量同步。
4. 初始化验收检查三份发布资料 + 两端入口均存在；已有内容只追加/补缺，不覆盖用户规则。

## 版本

- 与用户级一致：git commit hash 追踪，不维护线性版本号。
