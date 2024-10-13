class Flower private constructor(val name: String) { //outer 클래스
    companion object bud { // 이름 생략 가능 Flower 객체가 만들어지지 않아도 bud라는 객체 사용 가능
        private var numFlowers: Int = 0 // 클래스 하나를 만드는 것처럼 멤버 변수, 함수 나열 가능
        fun bloom(name: String): Flower? {
            if (numFlowers > 0)
                return null //객체는 단 한 개만 생성
            numFlowers++
            return Flower(name) // 객체에 접근이 가능
        }
    }

    override fun toString(): String {
        return "Flower $name"
    }
}

class Outer {
    val ov = 5
    class Nested { // 거의 남남에 가까워서 nested를 만들었다고 outer 생성 안 되어서 접근 불가
        val nv = 10
        fun greeting() = "Nested Flower"
        fun accessCompanionMethod() {
            println(country)
            getSomething() // 그냥 호출
        }
    }
    companion object { // 자동으로 객체가 만들어져 있다
        const val country = "Russia"
        fun getSomething() = println("Get Country")
        fun call_nested_greeting() { Outer.Nested().greeting() } //nested 클래스의 객체를 생성
    } //outer 생성 않고도 nested를 생성했다고 생각하면 안 됨. Outer.은 문서 위치처럼 아우터클래스 안에 있다는 의미
    fun outside() {
        val msg = Nested().greeting() // nested 클래스의 객체 생성
        println(Nested().nv)
    }
}

class Smartphone(val model : String) {
    private val cpu = "i5-8900"
    var name: String = "Noname"
    fun func1() {
        println("name is $name")
    }
    inner class Inner {
        fun which_cpu() {
            println(cpu)
            getSomething()
        }
    }
    companion object {
        const val country = "Russia"
        fun getSomething() = println("Get Country")
        fun which_cpu() {
            println("I don't know...")
        }
    }
}