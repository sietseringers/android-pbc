# android-bpc

A small Android library that simultaneously contains an Android-build of the [PBC library](https://crypto.stanford.edu/pbc) and the [JPBC](http://gas.dia.unisa.it/projects/jpbc) library, enabling fast computations on elliptic curves and pairings on Android.

The library is essentially a thin wrapper around the following three native libraries.
 * `jpbc-pbc` is the [PBC library](https://crypto.stanford.edu/pbc/) itself, built using `nkd-build`.
 * `gmp` is the [GNU Multiple Precision Arithmetic Library](https://gmplib.org/); used by PBC for its calculations.
 * `jnidispatch` comes from [JNA](https://github.com/twall/jna) and is the .so file contained in [this](https://github.com/twall/jna/blob/master/dist/android-arm.jar) file.

For each of these builds are included for the ARM and ARMv7 platforms.

## Usage

Assuming you use gradle, perform the following steps:

 1. `git clone` this repository somewhere, `cd` to it and run `./gradlew install`.
 2. Add `compile "net.sietseringers:android-pbc:0.1"` to the dependencies of your app.
 3. Put the line
 ```java
 net.sietseringers.androidpbc.AndroidPBC.Initialize();
 ```
 somewhere before you use any of the functionality of JPBC in Java.

After this, you can use the entire API that JPBC provides in the `it.unisa.dia.gas` namespace, including the part that refers to the PBC wrapper. Whenever you do, the included native library is used. For example, you can create a asymmetric (Type 3) bilinear pairing (called ['Type D' by the PBC documentation](https://crypto.stanford.edu/pbc/manual/ch08s06.html)) as follows:

```java
import it.unisa.dia.gas.plaf.jpbc.pbc.PBCPairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pbc.curve.PBCTypeDCurveGenerator;
import it.unisa.dia.gas.jpbc.PairingParameters;

Pairing e = PBCPairingFactory.getPairing(new PBCTypeDCurveGenerator(9563).generate());
```

(Note that generating the curve parameters takes much longer than instantiating the pairing (which takes only ~ 0.5 seconds), so you should probably store and reuse the parameters that the `PBCTypeDCurveGenerator` constructor outputs.)

## Warning

I cannot guarantee that this software will work. I have run just a few tests on only my own Android device. It works fine under those circumstances but more testing is certainly needed, so use with care.

## Rebuilding GMP and PBC

The upper two libraries have been included as submodules in the `jni/` folder. Assuming you have the Android NDK installed, they can be compiled as follows:
    
    ./gradlew ndkBuild

This will place them in the `libs/` folder, after which they will be included in the final .aar file. For some reason this process also deletes the `libjnidispatch.so` files from the `libs/` folder,  you'll have to checkout those again.
