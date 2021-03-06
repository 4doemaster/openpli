DESCRIPTION = "GSM/GPRS mux initializer"
LICENSE  = "GPL"
SECTION = "gpe"
PRIORITY = "optional"
DEPENDS  = "glibc"
PR = "r0"

inherit gpephone

SRC_URI = "${GPEPHONE_MIRROR}/GPRS/GPRS-${PV}.tar.bz2 \
           file://oe-install.patch"


S  = ${WORKDIR}/GPRS/cms92init-simple

do_compile() {
        oe_runmake PREFIX=${prefix}
}

do_install() {
        oe_runmake PREFIX=${prefix} DESTDIR=${D} install
}

SRC_URI[md5sum] = "ef2fb8e53946387922ee9e98fb0fe94b"
SRC_URI[sha256sum] = "fffcf07aba1ef5aef03defc88449f6ae0929813e9fcfda65c0d12d7b5c765248"
