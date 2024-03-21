package com.example.stopwatchwithnav

class timerTest {
    @Test
    fun `test formatTime`() {
        val fragment = HomeFragment()
        val formattedTime = fragment.formatTime(3661)
        assertEquals("01:01:01", formattedTime)
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel()

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
