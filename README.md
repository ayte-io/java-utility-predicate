# Ayte :: Utility :: Predicate

![CircleCI branch](https://img.shields.io/circleci/project/github/ayte-io/java-utility-predicate/release/0.1.svg?style=flat-square)
![Maven Central](https://img.shields.io/maven-central/v/io.ayte.utility.predicate/parent.svg?style=flat-square)

Tired of `.filter(collection -> collection.stream.anyMatch(CONSTANT::equals))`?
We're here.

## Coordinates

This project is separated into two packages, 
`io.ayte.utility.predicate:api` with interfaces and 
`io.ayte.utility.predicate:kit` with actual implementations. Contents
are both Java 8-compatible and exposed as 
`io.ayte.utility.predicate.api` / `io.ayte.utility.predicate.kit` java
9+ modules.

Check [Utility](https://github.com/ayte-io/java-utility) for better 
explanation of internal organization.

## What's all the fuzz about?

Using streams, optionals and custom classes that use filtering is an
everyday life as for now. However, there are common points of pain that
this library tries to eliminate:

- Repeating long and ugly pieces of code, e.g.:

      .filter(collection -> collection.stream().allMatch(x -> x >= 3))
  
- Debugging hell with lambdas, when you have an instance of predicate
but don't know where does it come from and how it calculates the result:

      // that's basic xor operation, you know
      .filter(value -> alpha.test(value) != beta.test(value))

This library provides set of named classes to address both of those 
issues:

```
.filter(Predicates.allMatch(Predicates.gte(3)))
```

```
.filter(Predicates.xor(Predicates.gte(3), Predicates.keyOf(map)))
```

Or, with static imports:

```
.filter(allMatch(gte(3)))
```

```
.filter(xor(gte(3), keyOf(map)))
```

Or, speaking about xor operation:

```
.filter(gte(3).xor(keyOf(map)))
```

All those operations return named classes (`AllMatch`, `GreaterThan`,
`KeyOf`), which will help you debug the issues. Developer friendliness 
is a top priority here, so this library tries to simplify things when 
possible, e.g. if `OneOf` factory method is called with two or more 
`ConstantTrue`'s, it will immediately shortcut to `ConstantFalse`, and
of course we try to keep naming concise.

## API

`api` jar provides end user with three interfaces: `UnaryPredicate`,
`BinaryPredicate` and `TernaryPredicate`. First two simply extend
`Predicate` and `BiPredicate`, adding `.xor()` method, and third one
doesn't inherit anything but follows same conventions.

`kit` jar exposes `Predicates`, `BinaryPredicates` and 
`TernaryPredicates` static factory classes, which should be sufficient
to use library at full power. 

It is not recommended to call classes factory methods directly since 
their signature and location may change without warning.

## Licensing

MIT / UPL-1.0

Ayte Labs, 2019

Do what comes natural
