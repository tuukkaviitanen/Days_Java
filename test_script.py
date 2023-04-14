import os


ProjectName = "Days"

if (os.name == "nt"):  # ON WINDOWS: days works just fine
    AppName = "days"
else:  # ON LINUX AND OTHER PLATFORMS: includes ./ and ".exe" extension
    AppName = "./days.exe"

script_directory = os.path.dirname(os.path.realpath(__file__))
programDirectory = os.path.join(
    script_directory, "target")
os.chdir(programDirectory)


def TestDays(command):
    fullCommand = " ".join(["java", "-jar", "days.jar", command])
    print(fullCommand)
    os.system(fullCommand)
    print("\n")


TestDays("add --category Holiday --description Midsummer --date 1989-06-24")
TestDays("add --category Birthday --description \"Tuukka's Birthday\" --date 2000-05-23")
TestDays("add --description CurrentDay")

TestDays("list")
TestDays("list --categories birthday")
TestDays("list --category Birthday")
TestDays("list --description tuukka")
TestDays("list --description Tuukka")
TestDays("list --date 2000-05-23")
TestDays("list --before-date 2000-05-23")
TestDays("list --after-date 2000-05-23 --exclude")
TestDays("list --after-date 2000-05-23")
TestDays("list --before-date 2000-05-23 --exclude")

TestDays("list --today")
TestDays("delete --all --dry-run")
TestDays("delete --description tuukka")
TestDays("delete --description mid")
TestDays("delete --today")

input('Press ENTER to exit')
