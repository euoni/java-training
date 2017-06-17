package java8.ch01.ex11;

class AbstractAbstract implements IAbstract, JAbstract {
	@Override
	public void f() {
		// 実装する必要あり
	}
}

class DefaultDefault implements IDefault, JDefault {
	@Override
	public void f() {
		// 実装する必要あり
		IDefault.super.f();
		JDefault.super.f();
	}
}

class StaticStatic implements IStatic, JStatic {
	// スタティックメソッドは実装とは関係ない
}

class AbstractDefault implements IAbstract, JDefault {
	@Override
	public void f() {
		// 実装する必要あり
		JDefault.super.f();
	}
}

class AbstractStatic implements IAbstract, JStatic {
	@Override
	public void f() {
		// 実装する必要あり
		// スタティックメソッドは実装とは関係ない
	}
}

class DefaultStatic implements IDefault, JStatic {
	// デフォルトメソッドを継承
	// スタティックメソッドは実装とは関係ない
}

class SAbstract extends S implements IAbstract {
	// S.fを継承
}

class SDefault extends S implements IDefault {
	// S.fを継承
}

class SStatic extends S implements IStatic {
	// S.fを継承
}
