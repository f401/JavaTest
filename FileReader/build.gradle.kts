plugins {
    id("java")
}

group = "net.qpowei.filereader"
var debug = rootProject.ext.get("DEBUG") as Boolean

tasks.jar {
	var releaseName = "RELEASE"
	if (debug) {
		releaseName = "DEBUG"
		manifest {
		    attributes(Pair("Main-Class", "Main"))
	    }
	}
	//destinationDirectory.set(file("../outputs"))
	archiveFileName = "FileReader-${releaseName}.jar"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}


sourceSets {
	main {
		java {
		    srcDir("src")
			if (!debug) {
				exclude("Main.java")
			}
		}
	}
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}
