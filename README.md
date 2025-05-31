# 대학교 3학년 프로젝트 통합 리포지토리

이 리포지토리는 대학교 3학년 때 만든 여러 프로젝트들을 GitHub 잔디(contribution graph)를 유지하면서 통합한 저장소입니다.

## 🌟 특징

- **잔디 보존**: 모든 커밋 히스토리와 날짜가 보존되어 GitHub 잔디가 유지됩니다
- **프로젝트 분리**: 각 프로젝트가 독립적인 폴더로 깔끔하게 정리됩니다
- **히스토리 보존**: 각 프로젝트의 전체 개발 과정을 확인할 수 있습니다

## 📁 프로젝트 구조

통합 후 다음과 같은 구조로 정리됩니다:

```
third-year/
├── project1/          # 첫 번째 프로젝트
├── project2/          # 두 번째 프로젝트
├── project3/          # 세 번째 프로젝트
├── ...
├── integrate_repos.ps1  # 통합 스크립트
└── README.md
```

## 🚀 사용법

### 1. 통합 스크립트 실행

PowerShell에서 다음 명령어를 실행하세요:

```powershell
.\integrate_repos.ps1
```

### 2. 리포지토리 정보 입력

스크립트가 실행되면:
1. 통합할 각 리포지토리의 GitHub URL을 입력
2. 각 프로젝트가 저장될 폴더명 지정
3. 빈 URL을 입력하여 완료

예시:
```
리포지토리 1 URL: https://github.com/username/data-structures
폴더명: data-structures

리포지토리 2 URL: https://github.com/username/web-project
폴더명: web-project

리포지토리 3 URL: (빈 입력으로 완료)
```

### 3. GitHub에 업로드

통합이 완료되면 새로운 GitHub 리포지토리에 푸시하세요:

```bash
git remote add origin https://github.com/username/third-year.git
git branch -M main
git push -u origin main
```

## ⚠️ 주의사항

1. **백업**: 원본 리포지토리들은 삭제하지 마세요 (백업 목적)
2. **권한**: 모든 원본 리포지토리에 접근 권한이 있어야 합니다
3. **브랜치**: 기본적으로 main 브랜치를 가져오며, 없으면 master 브랜치를 시도합니다

## 🔍 결과 확인

통합 후 다음 명령어로 결과를 확인할 수 있습니다:

```bash
# 커밋 히스토리 확인
git log --oneline --graph --all

# 폴더 구조 확인
dir  # Windows
ls   # Linux/Mac

# 특정 프로젝트의 히스토리 확인
git log --oneline project1/
```

## 💡 Git Subtree의 장점

- **완전한 통합**: 서브모듈과 달리 완전히 통합된 단일 리포지토리
- **히스토리 보존**: 모든 커밋 정보와 날짜가 보존됨
- **잔디 유지**: GitHub contribution graph가 그대로 유지됨
- **독립성**: 각 프로젝트가 독립적인 폴더로 구분됨
