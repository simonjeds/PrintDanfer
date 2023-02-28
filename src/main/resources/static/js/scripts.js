window.addEventListener('load', () => {
  class CustomError extends Error {
    constructor(error) {
      super(JSON.stringify(error))
      this.objectError = error
    }
  }

  const state = { isLoading: false }
  const form = document.forms.nfe
  const spinner = document.querySelector('.spinner-overlay')
  const messageBox = document.querySelector('.message-box')

  function toggleLoading() {
    state.isLoading = !state.isLoading

    if (state.isLoading) {
      spinner.classList.remove('hide')
    } else {
      spinner.classList.add('hide') 
    }
  }

  const handler = async fn => {
    toggleLoading()
    const result = await fn()
      .then(async response => {
        if (response.ok) return response.blob()
        throw new CustomError(await response.json())
      })
      .then(response => [response])
      .catch(error => [null, error.objectError])
    toggleLoading()
    return result
  }

  ["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(event => {
    form.key.addEventListener(event, function() {
      this.value = this.value.replace(/\D/g, '')
    });
  });

  function showErrorMessage(message) {
    messageBox.querySelector('span').innerText = message
    messageBox.classList.add('show')
    setTimeout(() => messageBox.classList.remove('show'), 5000)
  }

  form.addEventListener('submit', async event => {
    event.preventDefault()

    const key = form.key.value

    if (!key || key.trim().length !== 44) {
      return showErrorMessage('Digite uma chave de nota válida.')
    }

    const [data, error] = await handler(() => fetch(`/danfe-printer/rest/print/by-nfe-key/${key}`))

    if (error) {
      const { message } = error
      return showErrorMessage(message);
    }

    const pdfBlob = new Blob([data], { type: 'application/pdf' });
    const pdfUrl = URL.createObjectURL(pdfBlob);
    window.open(pdfUrl);
  })
})