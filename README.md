# playframework_2_4_11_htmlunit_problem

[play-scala-anorm-example template](https://github.com/playframework/play-scala-anorm-example) ported to Play! 2.4.x (and simplified a little bit).

The problemmatic line is

```scala
            <input type="search" id="searchbox" name="f" value="@currentFilter" placeholder="Filter by computer name...">
```

in `app/views/list.scala.html` file.

With Play! Framework version 2.4.10 test succeeds.

With Play! Framework version 2.4.11 test fails.

How to make it work:

- change input type from `search` to `text`

or

- upgrade HtmlUnit version at least to `2.19` by adding:

```
libraryDependencies += "net.sourceforge.htmlunit" % "htmlunit" % "2.19" % "test"
```

HtmlUnit improved `search` type inputs in version `2.19` (see [this commit](https://sourceforge.net/p/htmlunit/code/11034/)).

Play! 2.4.10 uses Fluentlenium `0.10.3`, Selenium `2.44.0` and HtmlUnit `2.15`.

Play! 2.4.11 uses Fluentlenium `0.10.9`, Selenium `2.48.2` and HtmlUnit `2.18`.

Why Play! Framework 2.4.10 works with `search` type inputs and 2.4.11 requires dependency changes? I don't know.

Uncomment:
```xml
  <!-- Trace problems
  <logger name="com.gargoylesoftware.htmlunit" level="TRACE" />
  <logger name="org.apache.http.wire" level="TRACE" />
  -->
```

if `conf/logback.xml` file for more detailed logs and search for something like this:

- version not working:

```
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event focusout (Current Target: HTMLElement for HtmlBody[<body>]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event focusin (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event focus (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
```

- version working:

```
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event focus (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event input (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="A" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="A" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="A" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="A" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="A" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event input (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Ap" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Ap" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Ap" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Ap" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event input (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="App" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="App" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="App" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="App" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event input (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Appl" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Appl" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keydown (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Appl" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keypress (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Appl" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event input (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Apple" placeholder="Filter by computer name...">]);
[debug] - com.gargoylesoftware.htmlunit.html.DomElement - Firing Event keyup (Current Target: HTMLElement for HtmlSearchInput[<input type="search" id="searchbox" name="f" value="Apple" placeholder="Filter by computer name...">]);
```
