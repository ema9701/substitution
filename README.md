# Week 2 Assessment

## Introduction

This assessment utilizes many of the concepts we've covered so far during the cohort: collections, loops, conditional logic, etc. This is an individual exercise. It's "open Internet", meaning you're allowed to use any resources at your disposal except for each other. However, you're encouraged to use the Tech Elevator reading, lecture code and exercises before doing general searching.

## Description

The starter code contains the class `com.techelevator.Crypto`. Crypto encrypts and decrypts Strings using what's called a substitution cypher. In a substitution cypher, every letter is replaced with a different letter. For this exercise use the following substitutions:

```
Letter -> Encrypts To
  A    ->    N
  B    ->    J
  C    ->    F
  D    ->    Q
  E    ->    A
  F    ->    P
  G    ->    S
  H    ->    R
  I    ->    X
  J    ->    G
  K    ->    D
  L    ->    O
  M    ->    T
  N    ->    E
  O    ->    U
  P    ->    H
  Q    ->    M
  R    ->    V
  S    ->    B
  T    ->    C
  U    ->    W
  V    ->    K
  W    ->    Z
  X    ->    I
  Y    ->    L
  Z    ->    Y
```

Visualized a different way

```
Letter:     ABCDEFGHIJKLMNOPQRSTUVWXYZ
Ecrypts to: NJFQAPSRXGDOTEUHMVBCWKZILY
```
## Example

To encrypt `HELLO` replace the `H` with `R`, the `E` with `A`, etc. The final encrypted version of `HELLO` is `RAOOU`.

## Step One

Implement the `encrypt(String strToEncrypt)` method in `Crypto`. The method accepts a String and returns the encrypted version per the substitution cypher. For this step assume that `strToEncrypt` only contains uppercase letters.

When complete all of the `Step1...` tests will pass.

## Step Two

Enhance the `encrypt` method so that any characters in "strToEncrypt" that aren't A thru Z aren't modified but are included in the encrypted result.

For example, the string `"HELLO, WORLD!"` encrypts to `"RAOOU, ZUVOQ!"`

When complete all of the `Step2...` tests will pass.

## Step Three

Implement the `decrypt` method to decrypt strings. 

When complete all of the `Step3...` tests will pass.
