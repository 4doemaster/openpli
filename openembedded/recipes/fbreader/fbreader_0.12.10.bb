DESCRIPTION = "FBreader is an ebook reader"
HOMEPAGE = "http://www.fbreader.org"
SECTION = "x11/utils"
PRIORITY = "optional"
LICENSE = "GPLv2"
DEPENDS = "gtk+ enca expat bzip2 libgpewidget virtual/libiconv liblinebreak libfribidi curl"

SRC_URI = "http://www.fbreader.org/fbreader-sources-${PV}.tgz \
file://Makefile.patch \
file://gcc45.patch"
	   
# Set the defaults
READER_RESOLUTION ?= "1024x600"
READER_ARCH       ?= "desktop"
READER_UI         ?= "gtk"
READER_STATUS	  ?= "release"

# because of defaults overriden in machine config
PACKAGE_ARCH_om-gta01 = "${MACHINE_ARCH}"
PACKAGE_ARCH_om-gta02 = "${MACHINE_ARCH}"

FILES_${PN} += "${datadir}/FBReader ${datadir}/zlibrary ${libdir}/zlibrary"
FILES_${PN}-dbg += "${libdir}/zlibrary/ui/.debug/"

CFLAGS_append = " RESOLUTION=${READER_RESOLUTION} INSTALLDIR=${prefix}"
EXTRA_OEMAKE = "CC='${CXX}' LD='${CXX}' LDFLAGS='${LDFLAGS}' INCPATH='${STAGING_INCDIR}' LIBPATH='${STAGING_LIBDIR}'"

inherit pkgconfig

do_configure() {
	cd ${WORKDIR}/${PN}-${PV}
	mv makefiles/target.mk makefiles/target.mk.orig
	
	echo "TARGET_ARCH = ${READER_ARCH}" > makefiles/target.mk	
	echo "UI_TYPE = ${READER_UI}" >> makefiles/target.mk
	echo "TARGET_STATUS = ${READER_STATUS}" >> makefiles/target.mk
}

do_install() {
        oe_runmake install DESTDIR=${D} RESOLUTION=${READER_RESOLUTION}
}

SRC_URI[md5sum] = "da9ec4721efdb0ec0aaa182bff16ad82"
SRC_URI[sha256sum] = "328aec454db80e225aa0b5c31adef74bf62a14357482947e87e9731686b3c624"
