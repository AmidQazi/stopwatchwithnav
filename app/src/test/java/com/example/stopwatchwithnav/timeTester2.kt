package com.example.stopwatchwithnav

class timeTester2 {
    @Test
    fun `test formatTime`() {
        val fragment = DashboardFragment()
        val formattedTime = fragment.formatTime(3661)
        assertEquals("01:01:01", formattedTime)
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        viewModel = DashboardViewModel()
    }

    @Test
    fun testTimerFunctionality() {
        viewModel.startTimer()

        Thread.sleep(100)

        assertEquals(1, viewModel.timerValue.value?.toInt())

        viewModel.pauseTimer()

        Thread.sleep(1000)

        assertEquals(1, viewModel.timerValue.value?.toInt())

        viewModel.resetTimer()

        assertEquals(0, viewModel.timerValue.value?.toInt())
    }
}