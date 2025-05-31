# Third Year University Projects Integration Script
# This script integrates multiple repositories while preserving GitHub contribution graph (green squares)

Write-Host "=== Third Year University Projects Integration Script ===" -ForegroundColor Green

# Ask user for repository URLs
Write-Host "Enter the GitHub URLs of repositories you want to integrate." -ForegroundColor Yellow
Write-Host "You'll also specify a folder name for each project." -ForegroundColor Yellow
Write-Host "(Enter empty URL to finish)" -ForegroundColor Yellow

$userRepos = @()
$index = 1

while ($true) {
    $url = Read-Host "Repository $index URL"
    if ([string]::IsNullOrWhiteSpace($url)) {
        break
    }
    
    $folder = Read-Host "Folder name"
    if ([string]::IsNullOrWhiteSpace($folder)) {
        $folder = "project$index"
    }
    
    $userRepos += @{
        url = $url
        folder = $folder
    }
    
    $index++
}

if ($userRepos.Count -eq 0) {
    Write-Host "No repositories to integrate. Please run the script again and enter repository URLs." -ForegroundColor Red
    exit
}

Write-Host "The following repositories will be integrated:" -ForegroundColor Cyan
foreach ($repo in $userRepos) {
    Write-Host "  - $($repo.url) -> $($repo.folder)" -ForegroundColor White
}

$confirm = Read-Host "Continue? (y/N)"
if ($confirm -ne "y" -and $confirm -ne "Y") {
    Write-Host "Cancelled." -ForegroundColor Yellow
    exit
}

# Check if current directory is a git repository
if (-not (Test-Path ".git")) {
    Write-Host "Initializing git repository in current directory..." -ForegroundColor Yellow
    git init
    git commit --allow-empty -m "Initial commit for third-year projects integration"
}

# Add each repository as a subtree
foreach ($repo in $userRepos) {
    Write-Host "Integrating: $($repo.url) -> $($repo.folder)" -ForegroundColor Green
    
    try {
        # Add remote
        $remoteName = $repo.folder.Replace("-", "").Replace("_", "")
        
        # Check if remote already exists
        $existingRemotes = git remote
        if ($existingRemotes -contains $remoteName) {
            Write-Host "  Remote $remoteName already exists, removing..." -ForegroundColor Yellow
            git remote remove $remoteName
        }
        
        git remote add $remoteName $repo.url
        
        # Fetch
        Write-Host "  Fetching repository data..." -ForegroundColor Gray
        git fetch $remoteName
        
        # Add subtree (preserve history)
        Write-Host "  Merging as subtree..." -ForegroundColor Gray
        git subtree add --prefix=$($repo.folder) $remoteName main
        
        Write-Host "  ✓ Completed: $($repo.folder)" -ForegroundColor Green
    }
    catch {
        Write-Host "  ❌ Error occurred: $($repo.url)" -ForegroundColor Red
        Write-Host "  Error details: $($_.Exception.Message)" -ForegroundColor Red
        
        # Try master branch if main doesn't exist
        try {
            Write-Host "  Retrying with master branch..." -ForegroundColor Yellow
            git subtree add --prefix=$($repo.folder) $remoteName master
            Write-Host "  ✓ Completed: $($repo.folder)" -ForegroundColor Green
        }
        catch {
            Write-Host "  ❌ Failed with master branch as well" -ForegroundColor Red
        }
    }
}

Write-Host "=== Integration Complete ===" -ForegroundColor Green
Write-Host "You can verify the results with these commands:" -ForegroundColor Yellow
Write-Host "  git log --oneline --graph --all" -ForegroundColor White
Write-Host "  dir" -ForegroundColor White

Write-Host "`nImportant notes:" -ForegroundColor Cyan
Write-Host "- All commit history is preserved, maintaining your GitHub contribution graph" -ForegroundColor White
Write-Host "- Each project is stored in its specified folder" -ForegroundColor White
Write-Host "- You can now push this repository to GitHub" -ForegroundColor White 