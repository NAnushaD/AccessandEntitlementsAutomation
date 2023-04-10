Write-Host "****************************************************************************"
Write-Host "CQN IT Automation Runner"
Write-Host "****************************************************************************"
<#
    Author: cristian.arias.chaves@intel.com

    This script clones the test project and build if it is necessary, the test dll
    generated from this project is then used by nunit console to generate the results
    Requirements:
        * You must have a .NET project repository with selenium that can be run locally with nunit-console without issues.
    Disclaimers:        
        * Read carefully the description of each parameter
#>

Set-Variable -Name buildProject -Value $Args[0]  #Y or N
Set-Variable -Name projectTestRepository -Value $Args[1]  #Ex: gitlab.devtools.intel.com/CQNITTools/qa-domain/automationsuitegitlab.git
Set-Variable -Name solutionTestPath -Value $Args[2]  #Ex: automationsuitegitlab/AutomationSuite.sln
Set-Variable -Name gitUser -Value $Args[3]  #Git user
Set-Variable -Name gitToken -Value $Args[4]  #Git user token
Set-Variable -Name gitBranch -Value $Args[5]  #Ex: master


function Main {

    function Get-AutomationProject {
        Write-Host "Cloning automation test project..."
        git --version
		#cd AirShuttle
        git clone -b $gitBranch "http://$($gitUser):$($gitToken)@$($projectTestRepository)"
        $repositoryName = $projectTestRepository.Substring($projectTestRepository.LastIndexOf('/') + 1).Replace(".git", "")
		return $LASTEXITCODE
    }

    function Build-AutomationProject {
        Write-Host "Building project..."
        & MSBUILD $solutionTestPath /consoleloggerparameters:ErrorsOnly /maxcpucount /nologo /nodeReuse:false /p:Configuration=Release
        return $LASTEXITCODE
    }

    try {       
        $exitCode = Get-AutomationProject
        Write-Host "Exit code is $exitCode"
        If ((Test-Path -LiteralPath $solutionTestPath -PathType Leaf) -and $buildProject -eq 'Y') {
            $exitCode = Build-AutomationProject
        }
        if($exitCode -eq 128) {
            $exitCode = 0;
        }
        return $exitCode
    }
    catch { 
       Write-Host "There was an error cloning the repository $($projectTestRepository)"
       Write-Host $_
       return 1    
    }
   
}

return Main