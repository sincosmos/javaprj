package com.sincosmos.effectivejava.chapter1;

import java.math.BigInteger;
import java.util.Random;
import java.util.EnumSet;

public class StaticFactoryMethod {
	Random rnd = new Random(47);
	BigInteger prime = BigInteger.probablePrime(32, rnd);
}
